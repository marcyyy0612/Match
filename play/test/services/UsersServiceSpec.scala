package services

import javax.inject._
import org.scalatest.FunSpec
import models.User

class UsersServiceSpec extends FunSpec {
  val service = new UsersService

  describe("list") {
    describe("ユーザが存在しないとき") {
      it("Nilを返す") {
        val id = 0L
        val actual = service.list(id)
        assert(actual == Nil)
      }
    }
    describe("ユーザが存在するとき") {
      it("Seq(User(1L, marcy))を返す") {
        val id = 1L
        val actual = service.list(id)
        assert(actual == Seq(User(id, "marcy")))
      }
    }
  }
}
