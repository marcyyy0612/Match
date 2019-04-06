package common

import models.Errors
import play.api.libs.json.{ Json, Writes }
import play.api.mvc.{ Result, Results }

import scala.concurrent.{ ExecutionContext, Future }
import scalaz.{ EitherT, Functor }

trait EitherTResultHelper {

  implicit class RichEitherTWithFuture[B](eitherT: EitherT[Future, Errors, B]) {
    def toResult(f: B => Result)(implicit ec: ExecutionContext): Future[Result] = {
      eitherT.run.map(_.fold(_.toResult, f))
    }

    def toResult(implicit ec: ExecutionContext, writes: Writes[B]): Future[Result] = {
      eitherT.run.map(_.fold(_.toResult, b => Results.Ok(Json.toJson(b))))
    }
  }

  implicit class RichEitherT[F[_], B](eitherT: EitherT[F, Errors, B]) {
    /**
      * LeftのErrorsを toResultをつかってResultに変換する
      * Rightは、任意の関数を使用してResultにする
      */
    def toResult(f: B => Result)(implicit F: Functor[F]): F[Result] = {
      eitherT.fold(_.toResult, f)
    }

    def toResult(implicit F: Functor[F], writes: Writes[B]): F[Result] = {
      eitherT.fold(_.toResult, b => Results.Ok(Json.toJson(b)))
    }

  }

  implicit class RichEitherTResult[F[_]](eitherT: EitherT[F, Errors, Result]) {
    def toResult(implicit F: Functor[F]): F[Result] = {
      eitherT.fold(_.toResult, b => b)
    }
  }
}

