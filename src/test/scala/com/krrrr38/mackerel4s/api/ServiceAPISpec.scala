package com.krrrr38.mackerel4s
package api

import org.scalatest._
import org.scalatest.concurrent.ScalaFutures._

class ServiceAPISpec extends MockApiServerFun with Matchers {

  object MockHostAPI
      extends ServiceAPI
      with MackerelClientBase {
    override val setting: ClientSetting = mockSetting
    override val apiKey: String = ""
    override val userAgent: String = ""
  }

  describe("get service list through api") {
    it("return service") {
      val futureResponse =
        MockHostAPI.listServices
          .run

      whenReady(futureResponse, patience) { res =>
        // see src/test/resources/api/GET/v0/services.json
        res.services.size shouldBe 1
        val service = res.services(0)
        service.name shouldBe "website"
        service.memo shouldBe "memo"
        service.roles shouldBe List("app", "db")
      }
    }
  }
}