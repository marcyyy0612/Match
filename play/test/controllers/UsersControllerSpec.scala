package controllers

import org.mockito.Mockito._
import org.scalatest.FunSpec
import org.scalatest.mockito.MockitoSugar
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.libs.json.{Json, JsArray}
import play.api.mvc.Results._
import play.api.test.Helpers._
import play.api.test._
import services.UsersService
import models.User

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
        val id = 0L
        it("Nilを返す") {
          when(usersService.list(id)).thenReturn(Nil)
          val futureResult = controller.list(id).apply(FakeRequest(GET, "/users"))
          val result = contentAsJson(futureResult)
          assert(result == Json.obj("users" -> JsArray()))
        }
      }
      describe("ユーザが存在するとき") {
        it("Seq(User(1L, marcy))を返す") {
          val id = 1L
          val user = User(id , "marcy")
          when(usersService.list(user.id)).thenReturn(Seq(user))
          val futureResult = controller.list(id).apply(FakeRequest(GET, "/users"))
          val result = contentAsJson(futureResult)
          assert(result == Json.obj("users" -> Seq(user)))
        }
      }
    }
  }
}
