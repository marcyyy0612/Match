package models.errors

import play.api.libs.json.{JsObject, Json}

case class ErrorMessage(code: String, message: String) {
  def toJson: JsObject = Json.obj(
    "code" -> code,
    "message" -> message
  )
  override def toString: String = s"$code: $message"
}

