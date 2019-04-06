package services

import models.{Errors, User}
import org.mockito.Mockito._
import org.scalatest.FunSpec
import org.scalatest.mockito.MockitoSugar
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import repositories.UsersRepositoryJDBC

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future}

class UsersServiceSpec(implicit executionContext: ExecutionContext)
  extends FunSpec
    with MockitoSugar
    with GuiceOneAppPerTest {
  val repository: UsersRepositoryJDBC = mock[UsersRepositoryJDBC]
  val service = new UsersService(repository)

  describe("list") {
    describe("ユーザが存在しないとき") {
      it("Errors.notFoundを返す") {
        when(repository.list).thenReturn(
          Future.successful(Nil)
        )
        val actual = service.list
        val result = Await.result(actual.run, Duration.Zero)
        assert(result == Errors.notFound())
      }
    }
    describe("ユーザが存在するとき") {
      it("Seq(User(1L, marcy))を返す") {
        val users = Seq(User(1L, "marcy"))
        when(repository.list).thenReturn(
          Future.successful(users)
        )
        val actual = service.list
        val result = Await.result(actual.run, Duration.Zero)
        assert(result == users)
      }
    }
  }
}
