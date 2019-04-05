package services

import models.User
import org.mockito.Mockito._
import org.scalatest.FunSpec
import org.scalatest.mockito.MockitoSugar
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import repositories.UsersRepositoryJDBC

import scala.concurrent.ExecutionContext

class UsersServiceSpec(implicit executionContext: ExecutionContext)
    extends FunSpec
    with MockitoSugar
    with GuiceOneAppPerTest {
  val repository = mock[UsersRepositoryJDBC]
  val service = new UsersService(repository)

  describe("list") {
    describe("ユーザが存在しないとき") {
      it("Nilを返す") {
        when(repository.list).thenReturn()
        val actual = service.list
        assert(actual == Left("not found"))
      }
    }
    describe("ユーザが存在するとき") {
      it("Seq(User(1L, marcy))を返す") {
        val users = Seq(User(1L, "marcy"))
        when(repository.list).thenReturn()
        val actual = service.list
        assert(actual == users)
      }
    }
  }
}
