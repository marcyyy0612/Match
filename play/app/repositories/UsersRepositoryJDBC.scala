package repositories

import javax.inject.Inject
import models.Tables._
import models.User
import slick.jdbc.H2Profile.api._

import scala.concurrent.ExecutionContext

class UsersRepositoryJDBC @Inject()(implicit executionContext: ExecutionContext) {

  def list: DBIO[Seq[User]] = {
    Users.result.map(_.map(user => {
      User(user.id, user.name)
    }))
  }
}
