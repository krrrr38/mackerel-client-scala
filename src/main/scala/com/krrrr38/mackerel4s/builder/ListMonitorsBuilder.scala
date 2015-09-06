package com.krrrr38.mackerel4s.builder

import com.krrrr38.mackerel4s.model.MonitorsResponse
import com.krrrr38.mackerel4s.model.Types.Path
import dispatch.Req

object ListMonitorsBuilder extends APIBuilder[Unit] {
  override val FullPath = (_: Unit) => "/monitors"
  override val MethodVerb = MethodVerbGet

  def apply(client: (Path) => Req): ListMonitorsBuilder = ListMonitorsBuilder(baseRequest(client, ()))
}

private[builder] case class ListMonitorsBuilder(private val req: Req) extends RequestBuilder[MonitorsResponse] {
  /**
   * build request with parameters before run http request
   * @return
   */
  override protected def buildRequest: Req = req
}

