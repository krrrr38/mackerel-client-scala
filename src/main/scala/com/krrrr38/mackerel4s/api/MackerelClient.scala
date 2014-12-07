package com.krrrr38.mackerel4s
package api

import dispatch._

trait MackerelClient {
  val setting: ClientSetting
  val apiKey: String

  lazy val baseRequest: Req =
    Req(_.addHeader(setting.AUTH_HEADER_KEY, apiKey))
      .setContentType("application/json", "UTF-8")

  def client(path: String): Req = baseRequest.setUrl(setting.BASE_URL + path)
}
