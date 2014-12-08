package com.krrrr38.mackerel4s
package builder

import com.krrrr38.mackerel4s.model.Types.{ Path, HostID }
import dispatch.Req
import com.krrrr38.mackerel4s.model.HostResponse

object GetHostBuilder extends APIBuilder[HostID] {
  override val FullPath = (hostId: HostID) => s"/hosts/$hostId"
  override val MethodVerb: MethodVerb = MethodVerbGet

  def apply(client: Path => Req, hostId: HostID): GetHostBuilder =
    GetHostBuilder(baseRequest(client, hostId))
}

private[builder] case class GetHostBuilder(private val req: Req) extends RequestBuilder[HostResponse] {
  /**
   * build request with parameters before run http request
   * @return
   */
  override protected def buildRequest: Req = req
}
