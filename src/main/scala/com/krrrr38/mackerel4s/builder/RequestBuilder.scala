package com.krrrr38.mackerel4s
package builder

import dispatch._
import org.json4s._
import com.fasterxml.jackson.core.JsonParseException

import scala.concurrent.ExecutionContext.Implicits.global
import scala.reflect.{ ClassTag, classTag }

import com.krrrr38.mackerel4s.model.{ MackerelClientException, MackerelResponseException, APIResponse }
import com.krrrr38.mackerel4s.model.Types.Path

sealed trait MethodVerb {
  val setMethod: Req => Req
}
case object MethodVerbGet extends MethodVerb {
  override val setMethod: (Req) => Req = _.GET
}
case object MethodVerbPost extends MethodVerb {
  override val setMethod: (Req) => Req = _.POST
}
case object MethodVerbPut extends MethodVerb {
  override val setMethod: (Req) => Req = _.PUT
}

trait APIBuilder[A] {
  val FullPath: A => String
  val MethodVerb: MethodVerb
  def baseRequest(client: Path => Req, arg: A) =
    MethodVerb.setMethod(client(FullPath(arg)))
}

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
