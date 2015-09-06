package com.krrrr38.mackerel4s
package api

import com.krrrr38.mackerel4s.model._
import org.scalatest._
import org.scalatest.concurrent.ScalaFutures._

class GraphDefAPISpec extends MockApiServerFun with Matchers {

  object MockHostAPI
      extends GraphDefAPI
      with MackerelClientBase {
    override val setting: ClientSetting = mockSetting
    override val apiKey: String = ""
    override val userAgent: String = ""
  }

  describe("create graph def through api") {
    it("return success") {
      val graphDef = GraphDef(Map("graphname" -> Graph("graphlabel", GraphUnitTypeInteger, Seq(GraphMetric("metricname", "metriclabel", false)))))
      val futureResponse =
        MockHostAPI.createGraphDef(Seq(graphDef))
          .run

      whenReady(futureResponse, patience) { res =>
        res.success shouldBe true
      }
    }
  }
}