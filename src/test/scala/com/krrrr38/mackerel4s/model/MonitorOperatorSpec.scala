package com.krrrr38.mackerel4s.model

import org.scalatest.{ FunSpec, Matchers }

class MonitorOperatorSpec extends FunSpec with Matchers {

  describe("MonitorOperator#toString") {
    it("convert successfully") {
      MonitorOperatorLT.toString shouldBe "<"
      MonitorOperatorGT.toString shouldBe ">"
    }

    describe("MonitorOperator#fromString") {
      it("convert successfully") {
        MonitorOperator.fromString("<") shouldBe Some(MonitorOperatorLT)
        MonitorOperator.fromString(">") shouldBe Some(MonitorOperatorGT)
        MonitorOperator.fromString("yunotti") shouldBe None
      }
    }
  }
}
