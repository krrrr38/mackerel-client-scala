package com.krrrr38.mackerel4s.serializer

import com.krrrr38.mackerel4s.model._
import org.json4s.JsonAST.JString
import org.json4s.jackson.{ JsonMethods, Serialization }
import org.scalatest.{ FunSpec, Matchers }

class MonitorOperatorSerializerSpec extends FunSpec with Matchers {

  implicit val formats = MackerelSerializer.FORMATS

  describe("MonitorOperator") {
    it("success to serialize/deserialize `<`") {
      val jsonStr = Serialization.write(MonitorOperatorWrapper(MonitorOperatorLT))
      val json = JsonMethods.parse(jsonStr)
      (json \ "operator") shouldBe JString("<")
      json.extract[MonitorOperatorWrapper].operator shouldBe MonitorOperatorLT
    }
    it("success to serialize/deserialize `>`") {
      val jsonStr = Serialization.write(MonitorOperatorWrapper(MonitorOperatorGT))
      val json = JsonMethods.parse(jsonStr)
      (json \ "operator") shouldBe JString(">")
      json.extract[MonitorOperatorWrapper].operator shouldBe MonitorOperatorGT
    }
  }

  case class MonitorOperatorWrapper(operator: MonitorOperator)

}
