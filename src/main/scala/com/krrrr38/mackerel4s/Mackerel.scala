package com.krrrr38.mackerel4s

import com.krrrr38.mackerel4s.api._
import com.krrrr38.mackerel4s.model.Types._

trait ClientSetting {
  val API_VERSION: String
  val AUTH_HEADER_KEY: String
  val BASE_URL: String
}

object MackerelClientSetting extends ClientSetting {
  val API_VERSION = "v0"
  val AUTH_HEADER_KEY = "X-Api-Key"
  val BASE_URL = s"https://mackerel.io/api/$API_VERSION"
}

/**
 * This class consists Mackerel API Http Client.
 * All Request would returned Future response.
 *
 * Usage example:
 * {{{
 *   import com.krrrr38.mackerel4s.Mackerel
 *   val mackerel = new Mackerel("api-key")
 *   mackerel.listHosts.setService("service-name").run
 * }}}
 *
 * @see [[http://help-ja.mackerel.io/entry/spec/api/v0]]
 * @param apiKey get from [[https://mackerel.io/my?tab=overview#apikey]]
 */
class Mackerel(val apiKey: ApiKey)
    extends MackerelClient
    with HostAPI
    with TsdbAPI
    with ServiceTsdbAPI {
  val setting: ClientSetting = MackerelClientSetting
}
