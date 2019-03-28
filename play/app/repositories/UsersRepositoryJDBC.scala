package repositories

import javax.inject.Inject
import models.User
import models.Tables._
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile
import slick.driver.H2Driver.api._

import scala.concurrent.ExecutionContext

class UsersRepositoryJDBC @Inject()(
    protected val dbConfigProvider: DatabaseConfigProvider)(
    implicit executionContext: ExecutionContext)
    extends HasDatabaseConfigProvider[JdbcProfile] {

  def list = {
    val result = Users.result.map(_.map(user => {
      User(user.id, user.name)
    }))
    db.run(result)
  }
}
