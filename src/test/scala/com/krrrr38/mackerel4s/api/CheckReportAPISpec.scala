package com.krrrr38.mackerel4s
package api

import com.krrrr38.mackerel4s.model._
import org.scalatest._
import org.scalatest.concurrent.ScalaFutures._

class CheckReportAPISpec extends MockApiServerFun with Matchers {

  object MockHostAPI
      extends CheckReportAPI
      with MackerelClientBase {
    override val setting: ClientSetting = mockSetting
    override val apiKey: String = ""
    override val userAgent: String = ""
  }

  describe("post check report through api") {
    it("return success") {
      val futureResponse =
        MockHostAPI.postCheckReports(Seq(
          checkreport(CheckReportStatusOK),
          checkreport(CheckReportStatusWarning),
          checkreport(CheckReportStatusCritical),
          checkreport(CheckReportStatusUnknown)
        )).run

      whenReady(futureResponse, patience) { res =>
        res.success shouldBe true
      }
    }
  }

  def checkreport(status: CheckReportStatus) =
    CheckReport(ReportSource("hostId"), "name", status, "message", System.currentTimeMillis())

}