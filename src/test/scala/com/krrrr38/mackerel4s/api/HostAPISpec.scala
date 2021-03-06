package com.krrrr38.mackerel4s
package api

import com.krrrr38.mackerel4s.model.{ Interface, HostStatusStandby, HostStatusWorking }
import org.scalatest._
import org.scalatest.concurrent.ScalaFutures._

class HostAPISpec extends MockApiServerFun with Matchers {

  object MockHostAPI
      extends HostAPI
      with MackerelClientBase {
    override val setting: ClientSetting = mockSetting
    override val apiKey: String = ""
    override val userAgent: String = ""
  }

  describe("get host list through api") {
    it("return hosts info") {
      val futureResponse =
        MockHostAPI.listHosts
          .setName("host_name")
          .setService("myservice")
          .addRole("role2")
          .addRoles(Seq("role1"))
          .addStatus(HostStatusWorking)
          .addStatuses(Seq(HostStatusStandby))
          .run

      // to get result, it takes not small time...
      whenReady(futureResponse, patience) { res =>
        res.hosts.length shouldBe 2

        val host = res.hosts.head
        host.name shouldBe "Layra.local"
        host.isRetired shouldBe false
      }
    }
  }

  describe("create host through api") {
    it("return host id, if success") {
      val futureResponse =
        MockHostAPI.createHost("host_name")
          .setName("host_name")
          .setMeta(org.json4s.JObject())
          .addRoleFullname("myservice:role1")
          .addRoleFullnames(Seq("myservice:role2"))
          .addInterface(Interface("name1", "ip1", "mac_address1"))
          .addInterfaces(Seq(Interface("name2", "ip2", "mac_address2")))
          .run

      whenReady(futureResponse, patience) { res =>
        res.id shouldBe "host_id"
      }
    }
  }

  describe("get host through api") {
    it("return a host info") {
      val futureResponse = MockHostAPI.getHost("host_id").run

      whenReady(futureResponse, patience) { res =>
        res.host.name shouldBe "Layra.local"
        res.host.isRetired shouldBe false
        res.host.status shouldBe HostStatusStandby
      }
    }
  }

  describe("update host through api") {
    it("return host id, if success") {
      val futureResponse = MockHostAPI.updateHost("host_id", "host_name").run

      whenReady(futureResponse, patience) { res =>
        res.id shouldBe "host_id"
      }
    }
  }

  describe("update host status through api") {
    it("return success info, if success") {
      val futureResponse = MockHostAPI.updateHostStatus("host_id", HostStatusStandby).run

      whenReady(futureResponse, patience) { res =>
        res.success shouldBe true
      }
    }
  }

  describe("retire host through api") {
    it("return success info, if success") {
      val futureResponse = MockHostAPI.retireHost("host_id").run
      whenReady(futureResponse, patience) { res =>
        res.success shouldBe true
      }
    }
  }
}