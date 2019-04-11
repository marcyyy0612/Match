package repositories

import javax.inject.Inject
import models.Tables._
import slick.jdbc.H2Profile.api._
import slick.jdbc.JdbcProfile
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}

import scala.concurrent.{ExecutionContext, Future}

class AuthRepositoryJDBC @Inject()(
  protected val dbConfigProvider: DatabaseConfigProvider)(
  implicit executionContext: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile] {

  def signin(email: String): Future[String] = {
    val result = Users.result.map(_.filter(user => {
      user.email.equals(email.bind)
    }).map(_.password))
    db.run(result.map(_.head))
  }
}
