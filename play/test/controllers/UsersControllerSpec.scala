package controllers

import models.User
import org.mockito.Mockito._
import org.scalatest.FunSpec
import org.scalatest.mockito.MockitoSugar
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.libs.json.{JsArray, Json}
import play.api.test.Helpers._
import play.api.test._
import services.UsersService

class UsersControllerSpec
    extends FunSpec
    with MockitoSugar
    with GuiceOneAppPerTest {
  val usersService = mock[UsersService]
  val controller = new UsersController(stubControllerComponents(), usersService)

  describe("GET") {
    describe("list") {
      describe("ユーザが存在しないとき") {
        it("Nilを返す") {
          when(usersService.list).thenReturn(Nil)
          val futureResult = controller.list.apply(FakeRequest(GET, "/users"))
          val result = contentAsJson(futureResult)
          assert(result == Json.obj("users" -> JsArray()))
        }
      }
      describe("ユーザが存在するとき") {
        it("Seq(User(1L, marcy))を返す") {
          val user = User(1L , "marcy")
          when(usersService.list).thenReturn(Seq(user))
          val futureResult = controller.list.apply(FakeRequest(GET, "/users"))
          val result = contentAsJson(futureResult)
          assert(result == Json.obj("users" -> Seq(user)))
        }
      }
    }
  }
}
