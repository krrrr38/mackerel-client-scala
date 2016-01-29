package com.krrrr38.mackerel4s.serializer

import com.krrrr38.mackerel4s.model._
import org.json4s.JsonAST.JString
import org.json4s.jackson.{ JsonMethods, Serialization }
import org.scalatest.{ Matchers, FunSpec }

class AlertStatusSerializerSpec extends FunSpec with Matchers {

  implicit val formats = MackerelSerializer.FORMATS

  describe("AlertStatusOperator") {
    it("success to serialize/deserialize `OK`") {
      val jsonStr = Serialization.write(AlertStatusWrapper(AlertStatusOK))
      val json = JsonMethods.parse(jsonStr)
      (json \ "status") shouldBe JString("OK")
      json.extract[AlertStatusWrapper].status shouldBe AlertStatusOK
    }
    it("success to serialize/deserialize `CRITICAL`") {
      val jsonStr = Serialization.write(AlertStatusWrapper(AlertStatusCritical))
      val json = JsonMethods.parse(jsonStr)
      (json \ "status") shouldBe JString("CRITICAL")
      json.extract[AlertStatusWrapper].status shouldBe AlertStatusCritical
    }
    it("success to serialize/deserialize `WARNING`") {
      val jsonStr = Serialization.write(AlertStatusWrapper(AlertStatusWarning))
      val json = JsonMethods.parse(jsonStr)
      (json \ "status") shouldBe JString("WARNING")
      json.extract[AlertStatusWrapper].status shouldBe AlertStatusWarning
    }
    it("success to serialize/deserialize `UNKNOWN`") {
      val jsonStr = Serialization.write(AlertStatusWrapper(AlertStatusUnknown))
      val json = JsonMethods.parse(jsonStr)
      (json \ "status") shouldBe JString("UNKNOWN")
      json.extract[AlertStatusWrapper].status shouldBe AlertStatusUnknown
    }
  }

  case class AlertStatusWrapper(status: AlertStatus)

}