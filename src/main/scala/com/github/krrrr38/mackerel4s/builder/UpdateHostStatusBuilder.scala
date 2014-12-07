package com.github.krrrr38.mackerel4s
package builder

import com.github.krrrr38.mackerel4s.model.Types.HostStatus
import dispatch.Req
import org.json4s.NoTypeHints
import org.json4s.jackson.Serialization

import com.github.krrrr38.mackerel4s.model.SuccessResponse

object UpdateHostStatusBuilder {
  def apply(req: Req, status: HostStatus): UpdateHostStatusBuilder = UpdateHostStatusBuilder(req, UpdateHostStatusParams(status))
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
