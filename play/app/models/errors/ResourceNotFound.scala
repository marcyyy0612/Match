package models.errors

import models.Errors
import play.api.mvc.Result
import play.api.mvc.Results.NotFound

final case class ResourceNotFound(message: Option[String] = None)
    extends Errors {
  override def toResult: Result = NotFound(this.toJson)

  override lazy val msg: ErrorMessage =
    ErrorMessage("error.resource.notFound", message.getOrElse("error.notFound"))
}
