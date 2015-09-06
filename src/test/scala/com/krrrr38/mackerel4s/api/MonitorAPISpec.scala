package com.krrrr38.mackerel4s
package api

import com.krrrr38.mackerel4s.model._
import org.scalatest._
import org.scalatest.concurrent.ScalaFutures._

class MonitorAPISpec extends MockApiServerFun with Matchers {

  object MockHostAPI
      extends MonitorAPI
      with MackerelClientBase {
    override val setting: ClientSetting = mockSetting
    override val apiKey: String = ""
    override val userAgent: String = ""
  }

  describe("get monitor list through api") {
    it("return monitors") {
      val futureResponse =
        MockHostAPI.listMonitors
          .run

      whenReady(futureResponse, patience) { res =>
        // see src/test/resources/api/GET/v0/monitors.json
        res.monitors.size shouldBe 4
        val connectivityMonitor = res.monitors(0).asInstanceOf[ConnectivityMonitor]
        val hostMonitor = res.monitors(1).asInstanceOf[HostMonitor]
        val serviceMonitor = res.monitors(2).asInstanceOf[ServiceMonitor]
        val externalMonitor = res.monitors(3).asInstanceOf[ExternalMonitor]

        connectivityMonitor.id shouldBe "monitor_id"
        connectivityMonitor.`type` shouldBe MonitorTypeConnectivity
        connectivityMonitor.scopes shouldBe Nil
        connectivityMonitor.excludeScopes shouldBe Nil

        hostMonitor.id shouldBe "monitor_id"
        hostMonitor.`type` shouldBe MonitorTypeHost
        hostMonitor.name shouldBe "Memory %"
        hostMonitor.metric shouldBe "memory%"
        hostMonitor.operator shouldBe MonitorOperatorGT
        hostMonitor.warning shouldBe 85.0
        hostMonitor.critical shouldBe 92.0
        hostMonitor.duration shouldBe 5
        hostMonitor.scopes shouldBe Seq("website")
        hostMonitor.excludeScopes shouldBe Nil

        serviceMonitor.id shouldBe "monitor_id"
        serviceMonitor.`type` shouldBe MonitorTypeService
        serviceMonitor.name shouldBe "service monitor name"
        serviceMonitor.service shouldBe "website"
        serviceMonitor.metric shouldBe "foo.bar"
        serviceMonitor.operator shouldBe MonitorOperatorGT
        serviceMonitor.warning shouldBe 80.0
        serviceMonitor.critical shouldBe 100.0
        serviceMonitor.duration shouldBe 3

        externalMonitor.id shouldBe "monitor_id"
        externalMonitor.`type` shouldBe MonitorTypeExternal
        externalMonitor.name shouldBe "external connectivity"
        externalMonitor.url shouldBe "http://localhost"
        externalMonitor.service shouldBe "website"
      }
    }
  }

  describe("create host monitor through api") {
    it("return monitor, if success") {
      // see src/test/resources/api/POST/v0/monitors.json
      //{"id":"monitor_id","type":"host","name":"disk.aa-00.writes.delta","metric":"disk.aa-00.writes.delta","operator":">","warning":20000.0,"critical":400000.0,"duration":3,"scopes":["website"],"excludeScopes":[]}
      val futureResponse =
        MockHostAPI.createHostMonitor("disk.aa-00.writes.delta", 3, "disk.aa-00.writes.delta", MonitorOperatorGT, 20000L, 40000L, Seq("website"))
          .run

      whenReady(futureResponse, patience) { res =>
        res.id shouldBe "monitor_id"
        res.`type` shouldBe MonitorTypeHost
        res.name shouldBe "disk.aa-00.writes.delta"
        res.metric shouldBe "disk.aa-00.writes.delta"
        res.operator shouldBe MonitorOperatorGT
        res.warning shouldBe 20000
        res.critical shouldBe 400000
        res.duration shouldBe 3
        res.scopes shouldBe Seq("website")
        res.excludeScopes shouldBe Nil
      }
    }
  }

  describe("update host monitor through api") {
    it("return monitor id, if success") {
      // see src/test/resources/api/DELETE/v0/monitors/monitor_id.json
      val monitor = HostMonitor("monitor_id", "disk.aa-00.writes.delta", 3, "disk.aa-00.writes.delta", MonitorOperatorGT, 20000L, 40000L, Seq("website"), Nil)
      val futureResponse = MockHostAPI.updateMonitor(monitor).run

      whenReady(futureResponse, patience) { res =>
        res.id shouldBe "monitor_id"
      }
    }
  }

  describe("delete host monitor through api") {
    it("return monitor, if success") {
      // see src/test/resources/api/DELETE/v0/monitors/monitor_id.json
      val futureResponse = MockHostAPI.deleteMonitor("monitor_id").run

      whenReady(futureResponse, patience) { res =>
        res shouldBe an[HostMonitor]
        val result = res.asInstanceOf[HostMonitor]
        result.id shouldBe "monitor_id"
        result.`type` shouldBe MonitorTypeHost
        result.name shouldBe "disk.aa-00.writes.delta"
        result.metric shouldBe "disk.aa-00.writes.delta"
        result.operator shouldBe MonitorOperatorGT
        result.warning shouldBe 22000
        result.critical shouldBe 440000
        result.duration shouldBe 3
        result.scopes shouldBe Seq("website")
        result.excludeScopes shouldBe Nil
      }
    }
  }

}