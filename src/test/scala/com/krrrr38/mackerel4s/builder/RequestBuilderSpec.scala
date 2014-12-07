package com.krrrr38.mackerel4s
package builder

import com.fasterxml.jackson.core.JsonParseException
import com.krrrr38.mackerel4s.model.APIResponse
import org.json4s.MappingException
import org.scalamock.scalatest.MockFactory
import org.scalatest.concurrent.PatienceConfiguration
import org.scalatest.time.{ Span, Seconds }
import org.scalatest.{ FunSpec, Matchers }

import dispatch.{ Req, Res }

import org.scalatest.concurrent.ScalaFutures._

class RequestBuilderSpec extends FunSpec with MockFactory with Matchers {

  val patient = PatienceConfiguration.Timeout(Span(5, Seconds))
  case class ResponseWrapper(data: String) extends APIResponse
  object Builder extends RequestBuilder[ResponseWrapper] {
    override protected def buildRequest: Req = ???
  }

  def buildSimpleResponse(statusCode: Int, body: String): Res = {
    val res = stub[dispatch.Res]
    (res.getStatusCode _).when() returns statusCode
    (res.getResponseBody _).when() returns body
    res
  }

  import scala.concurrent.ExecutionContext.Implicits.global
  def genFail(msg: String): scala.concurrent.Future[Int] = scala.concurrent.Future { throw new Exception(msg) }

  describe("parse http response") {
    it("return error, if status is not success [MackerelResponseException]") {
      val futureResponse = Builder.parse(buildSimpleResponse(500, "yunotti"))
      whenReady(futureResponse.failed, patient) { v =>
        v shouldBe a[MackerelResponseException]
        v.asInstanceOf[MackerelResponseException].body shouldBe "yunotti"
      }
    }

    it("return error, if lack of json value (json format is correct) [MackerelClientException]") {
      val futureResponse = Builder.parse(buildSimpleResponse(200, """{"data?": "invalid_key" }"""))
      whenReady(futureResponse.failed, patient) { v =>
        v shouldBe a[MackerelClientException]
        v.getCause shouldBe a[MappingException]
      }
    }

    it("return error, if invalid json response comming (something wrong case) [MackerelClientException]") {
      val futureResponse = Builder.parse(buildSimpleResponse(200, "{{}"))
      whenReady(futureResponse.failed, patient) { v =>
        v shouldBe a[MackerelClientException]
        v.getCause shouldBe a[JsonParseException]
      }
    }
  }
}