package services

import common.EitherTHelper
import javax.inject.Inject
import models.Errors
import repositories.AuthRepositoryJDBC
import scalaz.{-\/, EitherT, \/-}

import scala.concurrent.{ExecutionContext, Future}

class AuthService @Inject()(authRepositoryJDBC: AuthRepositoryJDBC)(
    implicit executionContext: ExecutionContext)
    extends EitherTHelper {

  def signin(
      email: String,
      password: String): EitherT[Future, Errors, Boolean] = {
    authRepositoryJDBC
      .signin(email)
      .map {
        case Some(r) if r == password => \/-(true)
        case Some(l) if l != password => -\/(Errors.unAuthorized())
        case _ => -\/(Errors.notFound())
      }
      .toEitherT
  }
}
