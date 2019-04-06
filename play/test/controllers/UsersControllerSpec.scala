package controllers

import common.EitherTHelper
import org.scalatest.FunSpec
import org.scalatest.mockito.MockitoSugar
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.test.Helpers._
import services.UsersService

import scala.concurrent.ExecutionContext

class UsersControllerSpec(implicit ExecutionContext: ExecutionContext)
  extends FunSpec
    with MockitoSugar
    with GuiceOneAppPerTest
    with EitherTHelper {
  val usersService = mock[UsersService]
  val controller = new UsersController(stubControllerComponents(), usersService)

  describe("GET") {
    describe("list") {
      describe("ユーザが存在しないとき") {
        it("Errors.notFoundを返す") {
//          when(usersService.list).thenReturn(
//            Future.successful(-\/(Errors.notFound())).toEitherT
//          )
//          val futureResult = controller.list.apply(FakeRequest(GET, "/users"))
//          val result = contentAsJson(futureResult)
//          assert(result == Json.obj("users" -> JsArray()))
        }
      }
      describe("ユーザが存在するとき") {
        it("[{1L, marcy}]を返す") {
//          val user = User(1L , "marcy")
//          when(usersService.list).thenReturn(
//            Future.successful(\/-(Seq(user))).toEitherT
//          )
//          val futureResult = controller.list.apply(FakeRequest(GET, "/users"))
//          val result = contentAsJson(futureResult)
//          assert(result == Json.obj("users" -> Seq(user)))
        }
      }
    }
  }
}
