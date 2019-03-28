package repositories

import javax.inject.Inject
import models.User
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile

import scala.concurrent.ExecutionContext

class UsersRepositoryJDBC @Inject()(
    protected val dbConfigProvider: DatabaseConfigProvider)(
    implicit executionContext: ExecutionContext)
    extends HasDatabaseConfigProvider[JdbcProfile] {

  def list: Seq[User] = {
    val user: User = User(id = 1L, name = "marcy")
    Seq(user)
  }
}
