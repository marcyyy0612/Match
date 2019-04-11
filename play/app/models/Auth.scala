package models

import play.api.libs.json.Reads

object Auth {
  case class Signin(email: String, password: String)

  object Signin {
    implicit val signinReads: Reads[Signin] = {
      (__ \ "full_name").read[String] ~
        (__ \ "age").read[Int].map[String](_.toString)
      )(Signin)
    }
  }
}

