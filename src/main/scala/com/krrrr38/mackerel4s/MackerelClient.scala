package com.krrrr38.mackerel4s

import com.krrrr38.mackerel4s.api._
import com.krrrr38.mackerel4s.model.Types._

trait ClientSetting {
  val USER_AGENT_KEY = "User-Agent"
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
 *   import com.krrrr38.mackerel4s.MackerelClient
 *   import com.krrrr38.mackerel4s.model.MackerelResponseError
 *   val mackerel = new MackerelClient("api-key", "user-agent")
 *   mackerel.listHosts.setService("service-name").run onComplete {
 *     case Success(res) => ...
 *     case Failure(ex: MackereResponseError) => s.statusCode ...
 *     case Failure(ex) => ...
 *   }
 * }}}
 *
 * @see [[http://help-ja.mackerel.io/entry/spec/api/v0]]
 * @param apiKey get from [[https://mackerel.io/my?tab=overview#apikey]]
 */
class MackerelClient(val apiKey: ApiKey, val userAgent: String)
    extends MackerelClientBase
    with ServiceAPI
    with RoleAPI
    with HostAPI
    with GraphDefAPI
    with HostMetricAPI
    with ServiceMetricAPI
    with CheckReportAPI
    with MonitorAPI {
  /**
   * create mackerel api client with default user agent
   * @see MackerelClient#this(ApiKey, String)
   * @param apiKey
   */
  def this(apiKey: ApiKey) =
    this(apiKey, "mackerel-client-scala")

  val setting: ClientSetting = MackerelClientSetting
}
