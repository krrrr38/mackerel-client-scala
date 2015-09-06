package com.krrrr38.mackerel4s.serializer

import com.krrrr38.mackerel4s.model._
import org.json4s.JsonAST.JString
import org.json4s.jackson.{ JsonMethods, Serialization }
import org.scalatest.{ FunSpec, Matchers }

class GraphUnitTypeSerializerSpec extends FunSpec with Matchers {

  implicit val formats = MackerelSerializer.FORMATS

  describe("GraphUnitType") {
    it("success to serialize/deserialize `float`") {
      val jsonStr = Serialization.write(GraphUnitTypeWrapper(GraphUnitTypeFloat))
      val json = JsonMethods.parse(jsonStr)
      (json \ "typ") shouldBe JString("float")
      json.extract[GraphUnitTypeWrapper].typ shouldBe GraphUnitTypeFloat
    }
    it("success to serialize/deserialize `integer`") {
      val jsonStr = Serialization.write(GraphUnitTypeWrapper(GraphUnitTypeInteger))
      val json = JsonMethods.parse(jsonStr)
      (json \ "typ") shouldBe JString("integer")
      json.extract[GraphUnitTypeWrapper].typ shouldBe GraphUnitTypeInteger
    }
    it("success to serialize/deserialize `percentage`") {
      val jsonStr = Serialization.write(GraphUnitTypeWrapper(GraphUnitTypePercentage))
      val json = JsonMethods.parse(jsonStr)
      (json \ "typ") shouldBe JString("percentage")
      json.extract[GraphUnitTypeWrapper].typ shouldBe GraphUnitTypePercentage
    }
    it("success to serialize/deserialize `bytes`") {
      val jsonStr = Serialization.write(GraphUnitTypeWrapper(GraphUnitTypeBytes))
      val json = JsonMethods.parse(jsonStr)
      (json \ "typ") shouldBe JString("bytes")
      json.extract[GraphUnitTypeWrapper].typ shouldBe GraphUnitTypeBytes
    }
    it("success to serialize/deserialize `bytes/sec`") {
      val jsonStr = Serialization.write(GraphUnitTypeWrapper(GraphUnitTypeBytesSec))
      val json = JsonMethods.parse(jsonStr)
      (json \ "typ") shouldBe JString("bytes/sec")
      json.extract[GraphUnitTypeWrapper].typ shouldBe GraphUnitTypeBytesSec
    }
    it("success to serialize/deserialize `iops`") {
      val jsonStr = Serialization.write(GraphUnitTypeWrapper(GraphUnitTypeIOPS))
      val json = JsonMethods.parse(jsonStr)
      (json \ "typ") shouldBe JString("iops")
      json.extract[GraphUnitTypeWrapper].typ shouldBe GraphUnitTypeIOPS
    }
  }

  case class GraphUnitTypeWrapper(typ: GraphUnitType)

}
