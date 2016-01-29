package com.krrrr38.mackerel4s.builder

import com.krrrr38.mackerel4s.model.AlertsResponse
import com.krrrr38.mackerel4s.model.Types.Path
import dispatch.Req

object ListAlertsBuilder extends APIBuilder[Unit] {
  override val FullPath = (_: Unit) => "/alerts"
  override val MethodVerb = MethodVerbGet

  def apply(client: (Path) => Req): ListAlertsBuilder = ListAlertsBuilder(baseRequest(client, ()))
}

private[builder] case class ListAlertsBuilder(private val req: Req) extends RequestBuilder[AlertsResponse] {
  /**
   * build request with parameters before run http request
   * @return
   */
  override protected def buildRequest: Req = req
}
