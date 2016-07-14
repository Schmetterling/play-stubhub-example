package services

import com.google.inject.Inject
import configurations.StubhubConfiguration
import play.api.libs.ws._

import scala.concurrent.Future

class StubhubProvider @Inject()(ws: WSClient) extends StubhubConfiguration {

  def getEvent(eventId: String): Future[WSResponse] = {
    ws.url(s"$stubhub_api_url/catalog/events/v2/$eventId")
      .withHeaders(
        ("Authorization" -> s"Bearer $stubhub_app_token"),
        ("Accept" -> "application/json"),
        ("Accept-Encoding" -> "application/json")
      ).get()
  }

  def getVenue(venueId: String): Future[WSResponse] = {
    ws.url(s"$stubhub_api_url/catalog/venues/v2/$venueId")
      .withHeaders(
        ("Authorization" -> s"Bearer $stubhub_app_token"),
        ("Accept" -> "application/json"),
        ("Accept-Encoding" -> "application/json")
      ).get()
  }
}