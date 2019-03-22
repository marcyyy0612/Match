package services

import javax.inject._
import org.scalatest.FunSpec

class UsersServiceSpec extends FunSpec {
  val service = new UsersService

  describe("list") {
    describe("ユーザが存在するとき") {
      it("Some('marcy')を返す") {
        val actual = service.list()
        assert(actual == Some("marcy"))
      }
    }
  }
}
