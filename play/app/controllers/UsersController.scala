package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json.Json

import services.UsersService

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
class UsersController @Inject()(
    cc: ControllerComponents,
    usersService: UsersService
) extends AbstractController(cc) {

  def list() = Action { implicit request: Request[AnyContent] =>
    val result = usersService.list()
    Ok(Json.obj("users" -> result))
  }
}
