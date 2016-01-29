package com.krrrr38.mackerel4s
package api

import com.krrrr38.mackerel4s.model._
import org.scalatest._
import org.scalatest.concurrent.ScalaFutures._

class AlertAPISpec extends MockApiServerFun with Matchers {

  object MockAlertAPI
      extends AlertAPI
      with MackerelClientBase {
    override val setting: ClientSetting = mockSetting
    override val apiKey: String = ""
    override val userAgent: String = ""
  }

  describe("get alerts") {
    it("return success") {
      val futureResponse = MockAlertAPI.listAlerts.run

      whenReady(futureResponse, patience) { res =>
        res.alerts.length shouldBe 1

        val alert = res.alerts.head
        alert.id shouldBe "alert_id"
        alert.status shouldBe AlertStatusWarning
        alert.`type` shouldBe MonitorTypeHost
      }
    }
  }

  describe("close alert") {
    it("return success") {
      val futureResponse = MockAlertAPI.closeAlert("alert_id", "reason").run

      whenReady(futureResponse, patience) { res =>
        val alert = res
        alert.id shouldBe "alert_id"
        alert.status shouldBe AlertStatusOK
        alert.`type` shouldBe MonitorTypeHost
        alert.reason shouldBe Some("reason")
      }
    }
  }

}