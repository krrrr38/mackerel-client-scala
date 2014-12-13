package com.krrrr38.mackerel4s
package api

import dispatch._
import com.krrrr38.mackerel4s.model.Types.{ ApiKey, Path }

trait MackerelClientBase {
  val setting: ClientSetting
  val apiKey: ApiKey

  val baseRequest: Req =
    Req(_.addHeader(setting.AUTH_HEADER_KEY, apiKey))
      .setContentType("application/json", "UTF-8")

  val client = (path: Path) =>
    baseRequest.setUrl(setting.BASE_URL + path)
}

