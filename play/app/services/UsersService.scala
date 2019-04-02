package services

import javax.inject._
import models.User
import repositories.UsersRepositoryJDBC
import slick.dbio.DBIO

import scala.concurrent.ExecutionContext

class UsersService @Inject()(
  usersRepositoryJDBC: UsersRepositoryJDBC)(
  implicit executionContext: ExecutionContext){
  def list: DBIO[Either[String, Seq[User]]] = {
    for {
      futureResult <- usersRepositoryJDBC.list
    } yield {
      futureResult match {
        case user if user.nonEmpty => Right(user)
        case _ => Left("not found")
      }
    }
  }
}
