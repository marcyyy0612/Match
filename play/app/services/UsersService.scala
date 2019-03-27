package services

import models.User

class UsersService {
  def list(id: Long): Seq[User] = {
    if (id == 1L) {
      val user: User = User(id = id, name = "marcy")
      Seq(user)
    } else {
      Nil
    }
  }
}
