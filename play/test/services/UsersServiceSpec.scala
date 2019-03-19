package services

import javax.inject._
import org.scalatest.FunSpec

class UsersServiceSpec extends FunSpec {
  val service = new UsersService

  describe("list") {
    describe("ユーザが存在するとき") {
      it("hogeを返す") {
        val actual = service.list()
        assert(actual == Some("hoge"))
      }
    }
  }
}
