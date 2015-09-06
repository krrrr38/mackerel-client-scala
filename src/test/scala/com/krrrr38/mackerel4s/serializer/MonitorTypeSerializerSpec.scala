package com.krrrr38.mackerel4s
package serializer

import com.krrrr38.mackerel4s.model._
import org.json4s.JsonAST.JString
import org.json4s.jackson.{ Serialization, JsonMethods }
import org.scalatest.{ FunSpec, Matchers }

class MonitorTypeSerializerSpec extends FunSpec with Matchers {

  implicit val formats = MackerelSerializer.FORMATS

  describe("MonitorType") {
    it("success to serialize/deserialize `connectivity`") {
      val jsonStr = Serialization.write(MonitorTypeWrapper(MonitorTypeConnectivity))
      val json = JsonMethods.parse(jsonStr)
      (json \ "typ") shouldBe JString("connectivity")
      json.extract[MonitorTypeWrapper].typ shouldBe MonitorTypeConnectivity
    }
    it("success to serialize/deserialize `host`") {
      val jsonStr = Serialization.write(MonitorTypeWrapper(MonitorTypeHost))
      val json = JsonMethods.parse(jsonStr)
      (json \ "typ") shouldBe JString("host")
      json.extract[MonitorTypeWrapper].typ shouldBe MonitorTypeHost
    }
    it("success to serialize/deserialize `service`") {
      val jsonStr = Serialization.write(MonitorTypeWrapper(MonitorTypeService))
      val json = JsonMethods.parse(jsonStr)
      (json \ "typ") shouldBe JString("service")
      json.extract[MonitorTypeWrapper].typ shouldBe MonitorTypeService
    }
    it("success to serialize/deserialize `external`") {
      val jsonStr = Serialization.write(MonitorTypeWrapper(MonitorTypeExternal))
      val json = JsonMethods.parse(jsonStr)
      (json \ "typ") shouldBe JString("external")
      json.extract[MonitorTypeWrapper].typ shouldBe MonitorTypeExternal
    }
  }

  case class MonitorTypeWrapper(typ: MonitorType)
}
