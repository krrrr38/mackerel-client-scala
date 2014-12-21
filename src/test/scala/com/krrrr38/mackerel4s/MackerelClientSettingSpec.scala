package com.krrrr38.mackerel4s
import org.scalatest.{ FunSpec, Matchers }

class MackerelClientSettingSpec extends FunSpec with Matchers {
  val setting = MackerelClientSetting

  describe("client setting") {
    it("contain valid version") {
      setting.API_VERSION shouldBe "v0"
    }

    it("contain valid url") {
      setting.BASE_URL.startsWith("https://mackerel.io/api/") shouldBe true
    }

    it("contain valid header api key") {
      setting.AUTH_HEADER_KEY shouldBe "X-Api-Key"
    }

    it("contain valid header User-Agent key") {
      setting.USER_AGENT_KEY shouldBe "User-Agent"
    }
  }
}