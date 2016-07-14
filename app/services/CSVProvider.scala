package services

import com.github.tototoshi.csv.CSVWriter
import models._
import play.api.Play

object CSVProvider {

  def uploadEvent(event: Event): Unit = {
    val file = Play.current.getFile("/public/event_data.csv")
    var writer: CSVWriter = null;

    try {
      writer = CSVWriter.open(file, true)

      writer.writeRow(
        List(
          "ID", "Title",
          "Description", "Timezone",
          "CurrencyCode", "EventUrl",
          "EventDateUTC", "EventDateLocal",
          "SeoDescription", "PrimaryName",
          "SecondaryName", "PrimaryAct",
          "SeoTitle", "Keywords",
          "Locale", "Venue ID"
        )
      )

      writer.writeRow(
        List(
          event.id, event.title.getOrElse(""),
          event.description.getOrElse(""), event.timezone.getOrElse(""),
          event.currencyCode.getOrElse(""), event.eventUrl.getOrElse(""),
          event.eventDateUTC.getOrElse(""), event.eventDateLocal.getOrElse(""),
          event.eventMeta.seoDescription.getOrElse(""), event.eventMeta.primaryName.getOrElse(""),
          event.eventMeta.secondaryName.getOrElse(""), event.eventMeta.primaryAct.getOrElse(""),
          event.eventMeta.seoTitle.getOrElse(""), event.eventMeta.keywords.getOrElse(""),
          event.eventMeta.locale.getOrElse(""), event.venue.id
        )
      )
    } finally {
      if(writer != null) writer.close();
    }
  }

  def uploadVenue(venue: Venue): Unit = {
    val file = Play.current.getFile("/public/venue_data.csv")
    var writer: CSVWriter = null;

    try {
      writer = CSVWriter.open(file)

      writer.writeRow(
        List(
          "ID", "Name",
          "Description", "URL",
          "Latitude", "Longitude",
          "Timezone", "Status",
          "Address", "City",
          "State", "ZipCode",
          "Country", "SeoDescription",
          "HomeTeam", "title",
          "seoTitle", "keywords",
          "locale"
        )
      )

      writer.writeRow(
        List(
          venue.id, venue.name.getOrElse(""),
          venue.description.getOrElse(""), venue.url.getOrElse(""),
          venue.latitude.getOrElse(0.0), venue.longitude.getOrElse(0.0),
          venue.timezone.getOrElse(""), venue.status.getOrElse(""),
          venue.address.address1.getOrElse(""), venue.address.city.getOrElse(""),
          venue.address.state.getOrElse(""), venue.address.zipCode.getOrElse(""),
          venue.address.country.getOrElse(""), venue.venueMeta.seoDescription.getOrElse(""),
          venue.venueMeta.homeTeam.getOrElse(""), venue.venueMeta.title.getOrElse(""),
          venue.venueMeta.seoTitle.getOrElse(""), venue.venueMeta.keywords.getOrElse(""),
          venue.venueMeta.locale.getOrElse("")
        )
      )
    } finally {
      if(writer != null) writer.close();
    }
  }
}