package controllers

import javax.inject._
import akka.stream.Materializer
import logging.StubhubLogging
import models._
import play.api.libs.json.Json
import play.api.libs.ws.ahc.AhcWSClient
import play.api.mvc._
import services.{CSVProvider, StubhubProvider}
import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class StubhubController @Inject() (implicit val mat: Materializer)
  extends Controller
  with StubhubLogging {

  // event
  implicit val eventMetaFormat = Json.format[EventMeta]
  implicit val eventVenueFormat = Json.format[EventVenue]
  implicit val eventFormat = Json.format[Event]

  // venue
  implicit val addressFormat = Json.format[Address]
  implicit val venueMetaFormat = Json.format[VenueMeta]
  implicit val venueFormat = Json.format[Venue]

  def getEvent(id: String) = Action.async {
    new StubhubProvider(AhcWSClient()).getEvent(id) map { response =>
      response.status match {
        case OK =>
          val event: Event = response.json.as[Event]
          CSVProvider.uploadEvent(event)

          okLogged(s"Event data from 'http://www.stubhub.com/' ${response.json}", response.json)
        case BAD_REQUEST => badRequestLogged("The specified eventId isn't a positive integer")
        case UNAUTHORIZED => unauthorizedLogged("The server didn't recognize your authorization token.")
        case NOT_FOUND => notFoundLogged("The specified eventId doesn't identify an event.")
        case _ => internalServerErrorLogged("The server generated an unspecified error.")
      }
    }
  }

  def getVenue(id: String) = Action.async {
    new StubhubProvider(AhcWSClient()).getVenue(id) map { response =>
      response.status match {
        case OK =>
          val venue: Venue = response.json.as[Venue]
          CSVProvider.uploadVenue(venue)

          okLogged(s"Venue data from 'http://www.stubhub.com/' ${response.json}", response.json)
        case BAD_REQUEST => badRequestLogged("The specified venueId isn't a positive integer")
        case UNAUTHORIZED => unauthorizedLogged("The server didn't recognize your authorization token.")
        case NOT_FOUND => notFoundLogged("The specified venueId doesn't identify an venue.")
        case _ => internalServerErrorLogged("The server generated an unspecified error.")
      }
    }
  }
}