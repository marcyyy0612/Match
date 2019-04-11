package controllers

import common.EitherTResultHelper
import javax.inject._
import play.api.mvc._
import services.UsersService

import scala.concurrent.ExecutionContext

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
class UsersController @Inject()(
  cc: ControllerComponents,
  usersService: UsersService)(
  implicit executionContext: ExecutionContext)
  extends AbstractController(cc)
    with EitherTResultHelper {

  def list: Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    val result = usersService.list
    result.toResult
  }
}
