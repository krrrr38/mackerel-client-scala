package com.krrrr38.mackerel4s.serializer

import com.krrrr38.mackerel4s.model._
import org.json4s.JsonAST.JString
import org.json4s.jackson.{ JsonMethods, Serialization }
import org.scalatest.{ FunSpec, Matchers }

class HostStatusSerializerSpec extends FunSpec with Matchers {

  implicit val formats = MackerelSerializer.FORMATS

  describe("HostStatus") {
    it("success to serialize/deserialize `working`") {
      val jsonStr = Serialization.write(HostStatusWrapper(HostStatusWorking))
      val json = JsonMethods.parse(jsonStr)
      (json \ "status") shouldBe JString("working")
      json.extract[HostStatusWrapper].status shouldBe HostStatusWorking
    }
    it("success to serialize/deserialize `standby`") {
      val jsonStr = Serialization.write(HostStatusWrapper(HostStatusStandby))
      val json = JsonMethods.parse(jsonStr)
      (json \ "status") shouldBe JString("standby")
      json.extract[HostStatusWrapper].status shouldBe HostStatusStandby
    }
    it("success to serialize/deserialize `maintenance`") {
      val jsonStr = Serialization.write(HostStatusWrapper(HostStatusMaintenance))
      val json = JsonMethods.parse(jsonStr)
      (json \ "status") shouldBe JString("maintenance")
      json.extract[HostStatusWrapper].status shouldBe HostStatusMaintenance
    }
    it("success to serialize/deserialize `poweroff`") {
      val jsonStr = Serialization.write(HostStatusWrapper(HostStatusPoweroff))
      val json = JsonMethods.parse(jsonStr)
      (json \ "status") shouldBe JString("poweroff")
      json.extract[HostStatusWrapper].status shouldBe HostStatusPoweroff
    }
  }

  case class HostStatusWrapper(status: HostStatus)
}
