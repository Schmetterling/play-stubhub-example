package logging

import play.api.Logger
import play.api.libs.json.JsValue
import play.api.mvc._

trait StubhubLogging {

  def okLogged(message: String, body: JsValue): Result = {
    Logger.info(message)
    Results.Ok(body)
  }

  def badRequestLogged(message: String): Result = {
    Logger.warn(message)
    Results.BadRequest(message)
  }

  def unauthorizedLogged(message: String): Result = {
    Logger.warn(message)
    Results.Unauthorized(message)
  }

  def notFoundLogged(message: String): Result = {
    Logger.warn(message)
    Results.NotFound(message)
  }

  def internalServerErrorLogged(message: String): Result = {
    Logger.error(message)
    Results.InternalServerError(message)
  }
}