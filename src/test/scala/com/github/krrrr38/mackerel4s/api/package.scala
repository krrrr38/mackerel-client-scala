package com.github.krrrr38.mackerel4s

package object api {
  val mockSetting = new ClientSetting {
    override val API_VERSION: String = "v0"
    override val AUTH_HEADER_KEY: String = ""
    override val BASE_URL: String = s"${MockApiServer.url}/$API_VERSION"
  }
}
