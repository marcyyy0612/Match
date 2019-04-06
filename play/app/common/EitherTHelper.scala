package common

import models.Errors
import scalaz.{Applicative, EitherT, \/, \/-}

import scala.concurrent.Future

trait EitherTHelper {

  implicit class ToEitherT[A](a: A) {
    /**
      * 素の値 a: A を任意のFによる EitherT[F, Errors, A] に変換する
      *
      * Fには、Future/DBIO を想定する
      */
    def toEitherT[F[_]](implicit F: Applicative[F]): EitherT[F, Errors, A] = {
      val either: \/[Errors, A] = \/-(a)
      EitherT(F.point(either))
    }
  }

  implicit class RichEither[A, B](either: A \/ B) {
    /**
      * A \/ B を EitherT[F, A, B] に変換する.Fは、FutureかDBIOを想定
      */
    def toEitherT[F[_]](implicit F: Applicative[F]): EitherT[F, A, B] = {
      EitherT(F.point(either))
    }

    /** toEitherT って長いので et でいけるようにショートカット */
    def et[F[_]](implicit F: Applicative[F]): EitherT[F, A, B] = toEitherT
  }

  implicit class RichEitherFuture[A, B](eitherF: Future[A \/ B]) {

    def toEitherT: EitherT[Future, A, B] = EitherT[Future, A, B](eitherF)

    /** toEitherT って長いので et でいけるようにショートカット */
    def et: EitherT[Future, A, B] = toEitherT

  }
}
