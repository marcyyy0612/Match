package models.errors

import models.Errors
import play.api.mvc.Result
import play.api.mvc.Results.Unauthorized

final case class UnAuthorized(message: Option[String] = None)
    extends Errors {
  override def toResult: Result = Unauthorized(this.toJson)

  override lazy val msg: ErrorMessage =
    ErrorMessage("error.unAuthorized", message.getOrElse("error.unAuthorized"))
}
