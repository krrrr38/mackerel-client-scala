package com.krrrr38.mackerel4s.serializer

import com.krrrr38.mackerel4s.model._
import org.json4s.JsonAST.JString
import org.json4s.jackson.{ JsonMethods, Serialization }
import org.scalatest.{ FunSpec, Matchers }

class CheckReportStatusSerializerSpec extends FunSpec with Matchers {

  implicit val formats = MackerelSerializer.FORMATS

  describe("CheckReportStatusOperator") {
    it("success to serialize/deserialize `OK`") {
      val jsonStr = Serialization.write(CheckReportStatusWrapper(CheckReportStatusOK))
      val json = JsonMethods.parse(jsonStr)
      (json \ "status") shouldBe JString("OK")
      json.extract[CheckReportStatusWrapper].status shouldBe CheckReportStatusOK
    }
    it("success to serialize/deserialize `CRITICAL`") {
      val jsonStr = Serialization.write(CheckReportStatusWrapper(CheckReportStatusCritical))
      val json = JsonMethods.parse(jsonStr)
      (json \ "status") shouldBe JString("CRITICAL")
      json.extract[CheckReportStatusWrapper].status shouldBe CheckReportStatusCritical
    }
    it("success to serialize/deserialize `WARNING`") {
      val jsonStr = Serialization.write(CheckReportStatusWrapper(CheckReportStatusWarning))
      val json = JsonMethods.parse(jsonStr)
      (json \ "status") shouldBe JString("WARNING")
      json.extract[CheckReportStatusWrapper].status shouldBe CheckReportStatusWarning
    }
    it("success to serialize/deserialize `UNKNOWN`") {
      val jsonStr = Serialization.write(CheckReportStatusWrapper(CheckReportStatusUnknown))
      val json = JsonMethods.parse(jsonStr)
      (json \ "status") shouldBe JString("UNKNOWN")
      json.extract[CheckReportStatusWrapper].status shouldBe CheckReportStatusUnknown
    }
  }

  case class CheckReportStatusWrapper(status: CheckReportStatus)

}
