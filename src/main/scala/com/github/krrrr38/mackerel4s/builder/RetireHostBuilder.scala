package com.github.krrrr38.mackerel4s
package builder

import dispatch.Req
import org.json4s.JsonAST.JObject
import org.json4s.jackson.JsonMethods._

import com.github.krrrr38.mackerel4s.model._

case class RetireHostBuilder(private val req: Req) extends RequestBuilder[SuccessResponse] {
  /**
   * build request with parameters before run http request
   * @return
   */
  override protected def buildRequest: Req = req.setBody(compact(render(JObject())))
}
