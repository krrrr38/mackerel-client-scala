package com.krrrr38.mackerel4s
package builder

import dispatch.Req
import org.json4s.JsonAST.JObject
import org.json4s.jackson.JsonMethods._

import com.krrrr38.mackerel4s.model.SuccessResponse
import com.krrrr38.mackerel4s.model.Types.{ Path, HostID }

object RetireHostBuilder extends APIBuilder[HostID] {
  override val FullPath = (hostId: HostID) => s"/hosts/$hostId/retire"
  override val MethodVerb: MethodVerb = MethodVerbPost

  def apply(client: Path => Req, hostId: HostID): RetireHostBuilder =
    RetireHostBuilder(baseRequest(client, hostId))
}

private[builder] case class RetireHostBuilder(private val req: Req) extends RequestBuilder[SuccessResponse] {
  /**
   * build request with parameters before run http request
   * @return
   */
  override protected def buildRequest: Req = req.setBody(compact(render(JObject())))
}
