package com.krrrr38.mackerel4s
package model

import org.scalatest.{ FunSpec, Matchers }

class HostStatusSpec extends FunSpec with Matchers {

  describe("HostStatus#toString") {
    it("return error, if status is not success [MackerelResponseException]") {
      HostStatusWorking.toString shouldBe "working"
      HostStatusStandby.toString shouldBe "standby"
      HostStatusMaintenance.toString shouldBe "maintenance"
      HostStatusPoweroff.toString shouldBe "poweroff"
    }
  }

  describe("HostStatus#fromString") {
    it("return error, if status is not success [MackerelResponseException]") {
      HostStatus.fromString("working") shouldBe Some(HostStatusWorking)
      HostStatus.fromString("standby") shouldBe Some(HostStatusStandby)
      HostStatus.fromString("maintenance") shouldBe Some(HostStatusMaintenance)
      HostStatus.fromString("poweroff") shouldBe Some(HostStatusPoweroff)
      HostStatus.fromString("yunotti") shouldBe None
    }
  }
}