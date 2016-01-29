package com.krrrr38.mackerel4s.model

import org.scalatest.{ FunSpec, Matchers }

class AlertStatuSpec extends FunSpec with Matchers {

  describe("AlertStatus#toString") {
    it("change correctly") {
      AlertStatusOK.toString shouldBe "OK"
      AlertStatusCritical.toString shouldBe "CRITICAL"
      AlertStatusWarning.toString shouldBe "WARNING"
      AlertStatusUnknown.toString shouldBe "UNKNOWN"
    }
  }

  describe("AlertStatus#fromString") {
    it("change correctly") {
      AlertStatus.fromString("OK") shouldBe Some(AlertStatusOK)
      AlertStatus.fromString("CRITICAL") shouldBe Some(AlertStatusCritical)
      AlertStatus.fromString("WARNING") shouldBe Some(AlertStatusWarning)
      AlertStatus.fromString("UNKNOWN") shouldBe Some(AlertStatusUnknown)
      AlertStatus.fromString("yuno") shouldBe None
    }
  }
}