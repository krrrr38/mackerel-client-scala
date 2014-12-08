package com.krrrr38.mackerel4s
package builder

import dispatch.Req
import org.json4s.NoTypeHints
import org.json4s.jackson.Serialization

import com.krrrr38.mackerel4s.model.{ HostStatus, SuccessResponse }
import com.krrrr38.mackerel4s.model.Types.{ Path, HostID }

object UpdateHostStatusBuilder extends APIBuilder[HostID] {
  override val FullPath = (hostId: HostID) => s"/hosts/$hostId/status"
  override val MethodVerb = MethodVerbPost

  def apply(client: Path => Req, hostId: HostID, status: HostStatus): UpdateHostStatusBuilder =
    UpdateHostStatusBuilder(baseRequest(client, hostId), UpdateHostStatusParams(status))
}

private[builder] case class UpdateHostStatusParams(status: HostStatus)

private[builder] case class UpdateHostStatusBuilder(private val req: Req, params: UpdateHostStatusParams) extends RequestBuilder[SuccessResponse] {
  /**
   * build request with parameters before run http request
   * @return
   */
  override protected def buildRequest: Req = {
    implicit val formats = Serialization.formats(NoTypeHints)
    req.setBody(Serialization.write(params))
  }
}
