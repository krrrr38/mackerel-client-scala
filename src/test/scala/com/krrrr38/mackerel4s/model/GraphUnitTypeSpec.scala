package com.krrrr38.mackerel4s.model

import org.scalatest.{ FunSpec, Matchers }

class GraphUnitTypeSpec extends FunSpec with Matchers {

  describe("GraphUnitType#toString") {
    it("convert successfully") {
      GraphUnitTypeInteger.toString shouldBe "integer"
      GraphUnitTypeFloat.toString shouldBe "float"
      GraphUnitTypeBytes.toString shouldBe "bytes"
      GraphUnitTypeBytesSec.toString shouldBe "bytes/sec"
      GraphUnitTypePercentage.toString shouldBe "percentage"
      GraphUnitTypeIOPS.toString shouldBe "iops"
    }

    describe("GraphUnitType#fromString") {
      it("convert successfully") {
        GraphUnitType.fromString("integer") shouldBe Some(GraphUnitTypeInteger)
        GraphUnitType.fromString("float") shouldBe Some(GraphUnitTypeFloat)
        GraphUnitType.fromString("bytes") shouldBe Some(GraphUnitTypeBytes)
        GraphUnitType.fromString("bytes/sec") shouldBe Some(GraphUnitTypeBytesSec)
        GraphUnitType.fromString("percentage") shouldBe Some(GraphUnitTypePercentage)
        GraphUnitType.fromString("iops") shouldBe Some(GraphUnitTypeIOPS)
        GraphUnitType.fromString("yunotti") shouldBe None
      }
    }
  }
}