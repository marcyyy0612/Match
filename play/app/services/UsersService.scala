package services

class UsersService {
  def list(id: String): Option[String] = {
    if (id == "1") {
      Some("marcy")
    } else {
      None
    }
  }
}
