package services

import javax.inject._
import org.scalatest.FunSpec

class UsersServiceSpec extends FunSpec {
  val service = new UsersService

  describe("list") {
    describe("ユーザが存在しないとき") {
      it("Noneを返す") {
        val id = "0"
        val actual = service.list(id)
        assert(actual == None)
      }
    }
    describe("ユーザが存在するとき") {
      it("marcyを返す") {
        val id = "1"
        val actual = service.list(id)
        assert(actual == Some("marcy"))
      }
    }
  }
}
