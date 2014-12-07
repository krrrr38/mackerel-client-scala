package com.github.krrrr38.mackerel4s
package builder

import com.github.krrrr38.mackerel4s.model.APIResponse
import dispatch._
import org.json4s._
import com.fasterxml.jackson.core.JsonParseException

import scala.concurrent.ExecutionContext.Implicits.global
import scala.reflect.{ ClassTag, classTag }

trait RequestBuilder[A <: APIResponse] {
  /**
   * build request with parameters before run http request
   * @return
   */
  protected def buildRequest: Req

  /**
   * run built request and get api response
   * @return
   */
  def run(implicit m: Manifest[A]): scala.concurrent.Future[A] =
    Http(buildRequest).flatMap(parse)

  /**
   * parse api response to get Scala object
   * @param response
   * @param m
   * @return
   */
  def parse(response: Res)(implicit m: Manifest[A]): Future[A] = Future {
    implicit val formats = DefaultFormats
    val code = response.getStatusCode
    val body = response.getResponseBody
    if (200 <= code && code < 300) {
      val json = maybeFail[JValue, JsonParseException](body, jackson.JsonMethods.parse(body))
      maybeFail[A, MappingException](body, json.extract[A])
    } else {
      // XXX sometimes Mackerel return html when sending invalid request
      throw new MackerelResponseException(body)
    }
  }

  private def maybeFail[B, E <: Throwable: ClassTag](body: String, f: => B): B = {
    try {
      f
    } catch {
      case ex if classTag[E].runtimeClass.isInstance(ex) => throw new MackerelClientException(body, ex)
    }
  }
}

/**
 * When send invalid request, Mackerel send invalid response.
 * This class wrap Mackerel invalid response.
 * @param body
 */
class MackerelResponseException(val body: String) extends Exception()

/**
 * This class show mackerel-client-scala error.
 * such as invalid json serialization format and so on.
 * @param body
 * @param cause
 */
class MackerelClientException(val body: String, cause: Throwable) extends Exception(cause)
