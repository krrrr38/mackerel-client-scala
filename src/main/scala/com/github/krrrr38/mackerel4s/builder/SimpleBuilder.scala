package com.github.krrrr38.mackerel4s
package builder

import dispatch.Req
import com.github.krrrr38.mackerel4s.model.APIResponse

case class SimpleBuilder[A <: APIResponse](private val req: Req) extends RequestBuilder[A] {
  /**
   * build request with parameters before run http request
   * @return
   */
  override protected def buildRequest: Req = req
}
