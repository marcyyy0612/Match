package controllers

import javax.inject._
import play.api.libs.json.Json
import play.api.mvc._
import services.UsersService

import scala.concurrent.ExecutionContext

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
class UsersController @Inject()(
    cc: ControllerComponents,
    usersService: UsersService)(implicit executionContext: ExecutionContext)
    extends AbstractController(cc) {

  def list = Action.async { implicit request: Request[AnyContent] =>
    val result = usersService.list
    // result.map {
    //   case Right(r) => Ok(Json.obj("user" -> r))
    //   case Left(l)  => NotFound(Json.obj("user" -> l))
    // }
  }
}
