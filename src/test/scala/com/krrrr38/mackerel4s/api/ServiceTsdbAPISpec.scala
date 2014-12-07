package com.krrrr38.mackerel4s
package api

import com.krrrr38.mackerel4s.model.{ ServiceMetric, HostMetric, MetricValue }
import org.scalatest._

import org.scalatest.concurrent.ScalaFutures._

class ServiceTsdbAPISpec extends MockApiServerFun with Matchers {

  object MockServiceTsdbAPI
      extends ServiceTsdbAPI
      with MackerelClient {
    override val setting: ClientSetting = mockSetting
    override val apiKey: String = ""
  }

  describe("post host metric through api") {
    it("return success info, if success") {
      val futureResponse =
        MockServiceTsdbAPI.postServiceMetric("service_name", Seq(ServiceMetric("metric_name", 10.0, 141790955)))
          .addMetric(ServiceMetric("metric_name", 8.2, 141790855))
          .addMetrics(Seq(ServiceMetric("metric_name", 5.1, 141790755)))
          .run

      whenReady(futureResponse, patience) { res =>
        res.success shouldBe true
      }
    }

    it("return success info with java.util.Date") {
      import java.util.Date
      val futureResponse =
        MockServiceTsdbAPI.postServiceMetric("service_name", Seq(ServiceMetric("metric_name", 10.0, new Date())))
          .run
      whenReady(futureResponse, patience) { res =>
        res.success shouldBe true
      }
    }
  }
}