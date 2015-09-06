package com.krrrr38.mackerel4s.builder

import com.krrrr38.mackerel4s.model.Monitor
import com.krrrr38.mackerel4s.model.Types.{ MonitorID, Path }
import dispatch.Req

object DeleteMonitorBuilder extends APIBuilder[MonitorID] {
  override val FullPath = (monitorId: MonitorID) => s"/monitors/$monitorId"
  override val MethodVerb: MethodVerb = MethodVerbDelete

  def apply(client: (Path) => Req, monitorId: MonitorID): DeleteMonitorBuilder =
    DeleteMonitorBuilder(baseRequest(client, monitorId))
}

private[builder] case class DeleteMonitorBuilder(private val req: Req) extends RequestBuilder[Monitor] {
  override protected def buildRequest: Req = req
}
