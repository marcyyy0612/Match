package models

import models.errors._
import play.api.libs.json._
import play.api.mvc.Result

/**
  * アプリケーションのエラーを表現する<br/>
  *
  */
trait Errors {

  /**
    * エラーをResultに変換する
    * 型に応じて NotFound や BadRequest などのレスポンスコードを決める
    * さらにエラーメッセージを統一のJSONに変換する
    *
    * @return エラーの型に適したResult
    */
  def toResult: Result

  def msg: ErrorMessage

  def toJson: JsValue = msg.toJson

  override def toString: String = msg.toString

  def toLogString: String = this.toString
}

/**
  * Errors の具象クラスのインスタンス生成のファクトリとしての機能を提供する
  */
object Errors {
  def notFound(): Errors = ResourceNotFound()
  def notFound(msg: String): Errors = ResourceNotFound(Some(msg))
  def unAuthorized(): Errors = UnAuthorized()
  def unAuthorized(msg: String): Errors = UnAuthorized(Some(msg))
}
