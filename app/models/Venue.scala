package models

import play.api.libs.json.Json

case class Address(address1: Option[String],
                   city: Option[String],
                   state: Option[String],
                   zipCode: Option[String],
                   country: Option[String])

case class VenueMeta(seoDescription: Option[String],
                      homeTeam: Option[String],
                      title: Option[String],
                      seoTitle: Option[String],
                      keywords: Option[String],
                      locale: Option[String])

case class Venue(id: Long,
                 name: Option[String],
                 description: Option[String],
                 url: Option[String],
                 latitude: Option[Double],
                 longitude: Option[Double],
                 timezone: Option[String],
                 status: Option[String],
                 address: Address,
                 venueMeta: VenueMeta) {


  implicit val addressFormat = Json.format[Address]
  implicit val venueMetaFormat = Json.format[VenueMeta]
  implicit val venueFormat = Json.format[Venue]
}