package models

import play.api.libs.json.Json

case class EventVenue(id: Long)

case class EventMeta(seoDescription: Option[String],
                     primaryName: Option[String],
                     secondaryName: Option[String],
                     primaryAct: Option[String],
                     secondaryAct: Option[String],
                     seoTitle: Option[String],
                     keywords: Option[String],
                     locale: Option[String])

case class Event(id: Long,
                 title: Option[String],
                 description: Option[String],
                 timezone: Option[String],
                 currencyCode: Option[String],
                 eventUrl: Option[String],
                 eventDateUTC: Option[String],
                 eventDateLocal: Option[String],
                 venue: EventVenue,
                 eventMeta: EventMeta) {

  implicit val eventMetaFormat = Json.format[EventMeta]
  implicit val eventVenueFormat = Json.format[EventVenue]
  implicit val eventFormat = Json.format[Event]
}