package models

import play.api.libs.json._
import play.api.libs.json.Reads._
import play.api.libs.functional.syntax._

object Auth {
  case class Signin(email: String, password: String)
  object Signin {
    implicit val signinReads: Reads[Signin] = Json.reads[Signin]
  }
}

