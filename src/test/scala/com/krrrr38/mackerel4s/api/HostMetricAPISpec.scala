package com.krrrr38.mackerel4s
package api

import com.krrrr38.mackerel4s.model.{ MetricValue, HostMetric }
import org.scalatest._

import org.scalatest.concurrent.ScalaFutures._

class HostMetricAPISpec extends MockApiServerFun with Matchers {

  object MockHostMetricAPI
      extends HostMetricAPI
      with MackerelClientBase {
    override val setting: ClientSetting = mockSetting
    override val apiKey: String = ""
    override val userAgent: String = ""
  }

  describe("post host metric through api") {
    it("return success info, if success") {
      val futureResponse =
        MockHostMetricAPI.postHostMetircs(Seq(HostMetric("host_id", "metric_name", 10.0, 141790955)))
          .addMetric(HostMetric("host_id", "metric_name", 8.2, 141790855))
          .addMetrics(Seq(HostMetric("host_id", "metric_name", 5.1, 141790755)))
          .run

      whenReady(futureResponse, patience) { res =>
        res.success shouldBe true
      }
    }

    it("return success info with java.util.Date") {
      import java.util.Date
      val futureResponse =
        MockHostMetricAPI.postHostMetircs(Seq(HostMetric("host_id", "metric_name", 10.0, new Date())))
          .run

      whenReady(futureResponse, patience) { res =>
        res.success shouldBe true
      }
    }
  }

  describe("get latest host metric through api") {
    it("return host metric info, if success") {
      val futureResponse =
        MockHostMetricAPI.getLatestHostMetrics()
          .addHostId("host1")
          .addHostIds(Seq("host2"))
          .addName("loadavg5")
          .addNames(Seq("cpu.user.percentage"))
          .run

      whenReady(futureResponse, patience) { res =>
        // following results are according to test/resources/api/GET/v0/tsdb/latest.json
        res.tsdbLatest.size shouldBe 2
        val hostMetric = res.tsdbLatest.get("host1").get
        hostMetric.get("loadavg5") shouldBe Some(MetricValue(2.8375, 1417868700))
      }
    }
  }
}