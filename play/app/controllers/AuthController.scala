package controllers

import common.EitherTResultHelper
import javax.inject.Inject
import models.Auth.Signin
import play.api.libs.json.JsValue
import play.api.mvc._
import services.AuthService

import scala.concurrent.ExecutionContext

class AuthController @Inject()(
    cc: ControllerComponents,
    authService: AuthService)(implicit executionContext: ExecutionContext)
    extends AbstractController(cc)
    with EitherTResultHelper {

  def signIn: Action[JsValue] = Action.async(parse.json) { implicit request =>
    val reqBody = request.body.validate[Signin]
    val result = authService.signin(reqBody.get.email, reqBody.get.password)
    result.toResult
  }
}
