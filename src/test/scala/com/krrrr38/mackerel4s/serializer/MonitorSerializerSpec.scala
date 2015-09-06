package com.krrrr38.mackerel4s.serializer

import com.krrrr38.mackerel4s.model._
import org.json4s.JsonAST.{ JDouble, JInt, JArray, JString }
import org.json4s.jackson.{ JsonMethods, Serialization }
import org.scalatest.{ FunSpec, Matchers }

class MonitorSerializerSpec extends FunSpec with Matchers {

  implicit val formats = MackerelSerializer.FORMATS

  describe("Monitor") {
    it("success to serialize/deserialize ConnectivityMonitor") {
      val connectivityMonitor = ConnectivityMonitor("monitorid", Seq("scope1"), Nil)
      val jsonStr = Serialization.write(connectivityMonitor)
      val json = JsonMethods.parse(jsonStr)
      (json \ "type") shouldBe JString("connectivity")
      (json \ "id") shouldBe JString("monitorid")
      (json \ "scopes") shouldBe JArray(List(JString("scope1")))
      (json \ "excludeScopes") shouldBe JArray(Nil)
      val monitorResult = json.extract[Monitor]
      monitorResult shouldBe an[ConnectivityMonitor]
      val result = monitorResult.asInstanceOf[ConnectivityMonitor]
      result.id shouldBe "monitorid"
      result.`type` shouldBe MonitorTypeConnectivity
      result.scopes shouldBe Seq("scope1")
      result.excludeScopes shouldBe Nil
    }
    it("success to serialize/deserialize HostMonitor") {
      val hostMonitor = HostMonitor("monitorid", "name", 1, "metric", MonitorOperatorLT, 80, 100, Nil, Nil)
      val jsonStr = Serialization.write(hostMonitor)
      val json = JsonMethods.parse(jsonStr)
      (json \ "type") shouldBe JString("host")
      (json \ "id") shouldBe JString("monitorid")
      (json \ "name") shouldBe JString("name")
      (json \ "duration") shouldBe JInt(1)
      (json \ "metric") shouldBe JString("metric")
      (json \ "operator") shouldBe JString("<")
      (json \ "warning") shouldBe JInt(80)
      (json \ "critical") shouldBe JInt(100)
      (json \ "scopes") shouldBe JArray(Nil)
      (json \ "excludeScopes") shouldBe JArray(Nil)
      val monitorResult = json.extract[Monitor]
      monitorResult shouldBe an[HostMonitor]
      val result = monitorResult.asInstanceOf[HostMonitor]
      result.id shouldBe "monitorid"
      result.`type` shouldBe MonitorTypeHost
      result.name shouldBe "name"
      result.duration shouldBe 1
      result.metric shouldBe "metric"
      result.operator shouldBe MonitorOperatorLT
      result.warning shouldBe 80
      result.critical shouldBe 100
      result.scopes shouldBe Nil
      result.excludeScopes shouldBe Nil
    }
    it("success to serialize/deserialize ServiceMonitor") {
      val serviceMonitor = ServiceMonitor("monitorid", "name", "service", 1, "metric", MonitorOperatorGT, 80, 100)
      val jsonStr = Serialization.write(serviceMonitor)
      val json = JsonMethods.parse(jsonStr)
      (json \ "type") shouldBe JString("service")
      (json \ "id") shouldBe JString("monitorid")
      (json \ "name") shouldBe JString("name")
      (json \ "duration") shouldBe JInt(1)
      (json \ "metric") shouldBe JString("metric")
      (json \ "operator") shouldBe JString(">")
      (json \ "warning") shouldBe JInt(80)
      (json \ "critical") shouldBe JInt(100)
      val monitorResult = json.extract[Monitor]
      monitorResult shouldBe an[ServiceMonitor]
      val result = monitorResult.asInstanceOf[ServiceMonitor]
      result.id shouldBe "monitorid"
      result.`type` shouldBe MonitorTypeService
      result.name shouldBe "name"
      result.duration shouldBe 1
      result.metric shouldBe "metric"
      result.operator shouldBe MonitorOperatorGT
      result.warning shouldBe 80
      result.critical shouldBe 100
    }
    it("success to serialize/deserialize ExternalMonitor") {
      val externalMonitor = ExternalMonitor("monitorid", "name", "url", "service")
      val jsonStr = Serialization.write(externalMonitor)
      val json = JsonMethods.parse(jsonStr)
      (json \ "type") shouldBe JString("external")
      (json \ "id") shouldBe JString("monitorid")
      (json \ "name") shouldBe JString("name")
      (json \ "url") shouldBe JString("url")
      val monitorResult = json.extract[Monitor]
      monitorResult shouldBe an[ExternalMonitor]
      val result = monitorResult.asInstanceOf[ExternalMonitor]
      result.id shouldBe "monitorid"
      result.`type` shouldBe MonitorTypeExternal
      result.name shouldBe "name"
      result.url shouldBe "url"
    }
  }
}
