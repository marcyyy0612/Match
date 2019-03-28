package services

import javax.inject._
import models.User
import repositories.UsersRepositoryJDBC

import scala.concurrent.Future

class UsersService @Inject()(
    usersRepositoryJDBC: UsersRepositoryJDBC
) {
  def list: Future[Seq[User]] = {
    usersRepositoryJDBC.list
  }
}
