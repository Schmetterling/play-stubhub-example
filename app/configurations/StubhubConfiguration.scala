package configurations

trait StubhubConfiguration {
  lazy val config = play.Configuration.root()

  lazy val stubhub_app_token = config.getString("stubhub.app_token")
  lazy val stubhub_api_url = config.getString("stubhub.api_url")
}