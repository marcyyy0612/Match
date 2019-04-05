package services

import javax.inject._
import models.User
import repositories.UsersRepositoryJDBC
import scalaz.EitherT
import scalaz.EitherT._
import scalaz.{ -\/, \/-, EitherT, Applicative, \/ }
import services.EitherTHelper

import scala.concurrent.{ExecutionContext, Future}

class UsersService @Inject()(usersRepositoryJDBC: UsersRepositoryJDBC)(
    implicit executionContext: ExecutionContext)
    extends EitherTHelper {
  def list: EitherT[Future, String, Seq[User]] = {
    val result = for {
      futureResult <- usersRepositoryJDBC.list
    } yield {
      futureResult match {
        case user if user.nonEmpty => \/-(user)
        case _                     => -\/("not found")
      }
    }
    result.toEitherT
  }
}
