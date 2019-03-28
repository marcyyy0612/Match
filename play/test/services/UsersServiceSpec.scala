package services

import models.User
import org.mockito.Mockito._
import org.scalatest.FunSpec
import org.scalatest.mockito.MockitoSugar
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import repositories.UsersRepositoryJDBC

class UsersServiceSpec
    extends FunSpec
    with MockitoSugar
    with GuiceOneAppPerTest {
  val repository = mock[UsersRepositoryJDBC]
  val service = new UsersService(repository)

  describe("list") {
    describe("ユーザが存在しないとき") {
      it("Nilを返す") {
        when(repository.list).thenReturn(Nil)
        val actual = service.list
        assert(actual == Nil)
      }
    }
    describe("ユーザが存在するとき") {
      it("Seq(User(1L, marcy))を返す") {
        val users = Seq(User(1L, "marcy"))
        when(repository.list).thenReturn(users)
        val actual = service.list
        assert(actual == users)
      }
    }
  }
}
