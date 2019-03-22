package controllers

import javax.inject._
import org.scalatest.FunSpec
import org.mockito.Mockito._
import org.scalatest.mockito.MockitoSugar
import play.api._
import play.api.mvc._
import play.api.mvc.Results._
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test._

import services.UsersService

class UsersControllerSpec extends FunSpec with MockitoSugar {
  val usersService = mock[UsersService]
  val cc = mock[ControllerComponents]
  val controller = new UsersController(cc, usersService)

  describe("GET") {
    describe("list") {
      describe("ユーザが存在しないとき") {
        it("Noneを返す") {
          when(usersService.list()).thenReturn(None)
          println("GET /users")
          val actual = controller.list()(FakeRequest(GET, "/users"))
          println(s"$actual")
          assert(actual == Ok(Json.obj("users" -> null)))
        }
      }
    }
  }
}
