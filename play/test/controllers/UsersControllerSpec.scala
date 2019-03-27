package controllers

import org.mockito.Mockito._
import org.scalatest.FunSpec
import org.scalatest.mockito.MockitoSugar
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.libs.json.Json
import play.api.libs.json.{JsValue, JsNull}
import play.api.mvc.Results._
import play.api.test.Helpers._
import play.api.test._
import services.UsersService

import scala.concurrent.{ExecutionContext, Future}

class UsersControllerSpec
    extends FunSpec
    with MockitoSugar
    with GuiceOneAppPerTest {
  val usersService = mock[UsersService]
  val controller = new UsersController(stubControllerComponents(), usersService)

  describe("GET") {
    describe("list") {
      describe("ユーザが存在しないとき") {
        val id = "0"
        it("nullを返す") {
          when(usersService.list(id)).thenReturn(None)
          val futureResult = controller.list(id).apply(FakeRequest(GET, "/users"))
          val result = contentAsJson(futureResult)
          assert(result == Json.obj("users" -> JsNull))
        }
      }
      describe("ユーザが存在するとき") {
        val id = "1"
        it("marcyを返す") {
          when(usersService.list(id)).thenReturn(Some("marcy"))
          val futureResult = controller.list(id).apply(FakeRequest(GET, "/users"))
          val result = contentAsJson(futureResult)
          assert(result == Json.obj("users" -> "marcy"))
        }
      }
    }
  }
}
