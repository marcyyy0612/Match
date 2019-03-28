package services

import javax.inject._
import models.User
import repositories.UsersRepositoryJDBC

class UsersService @Inject()(
    usersRepositoryJDBC: UsersRepositoryJDBC
) {
  def list: Seq[User] = {
    usersRepositoryJDBC.list
  }
}
