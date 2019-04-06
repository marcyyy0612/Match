package services

import common.EitherTHelper
import javax.inject._
import models.{Errors, User}
import repositories.UsersRepositoryJDBC
import scalaz.{-\/, EitherT, \/-}

import scala.concurrent.{ExecutionContext, Future}

class UsersService @Inject()(usersRepositoryJDBC: UsersRepositoryJDBC)(
  implicit executionContext: ExecutionContext)
  extends EitherTHelper {
  def list: EitherT[Future, Errors, Seq[User]] = {
    usersRepositoryJDBC.list.map {
      case users if users.nonEmpty => \/-(users)
      case _ => -\/(Errors.notFound())
    }.toEitherT
  }
}
