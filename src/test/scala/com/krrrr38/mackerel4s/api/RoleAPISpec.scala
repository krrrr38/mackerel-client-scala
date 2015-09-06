package com.krrrr38.mackerel4s
package api

import org.scalatest._
import org.scalatest.concurrent.ScalaFutures._

class RoleAPISpec extends MockApiServerFun with Matchers {

  object MockHostAPI
      extends RoleAPI
      with MackerelClientBase {
    override val setting: ClientSetting = mockSetting
    override val apiKey: String = ""
    override val userAgent: String = ""
  }

  describe("get role list through api") {
    it("return roles") {
      val futureResponse =
        MockHostAPI.listRoles("service_name")
          .run

      whenReady(futureResponse, patience) { res =>
        // see src/test/resources/api/GET/v0/services/service_name/roles.json
        res.roles.size shouldBe 2
        val role = res.roles(0)
        role.name shouldBe "app"
        role.memo shouldBe "memo"
      }
    }
  }
}