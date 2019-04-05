package repositories

import javax.inject.Inject
import models.Tables._
import models.User
import slick.jdbc.H2Profile.api._
import slick.jdbc.JdbcProfile
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}

import scala.concurrent.{ExecutionContext, Future}

class UsersRepositoryJDBC @Inject()(
    protected val dbConfigProvider: DatabaseConfigProvider)(
    implicit executionContext: ExecutionContext)
    extends HasDatabaseConfigProvider[JdbcProfile] {

  def list: Future[Seq[User]] = {
    val result = Users.result.map(_.map(user => {
      User(user.id, user.name)
    }))
    db.run(result)
  }
}
