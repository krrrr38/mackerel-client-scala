package com.krrrr38.mackerel4s.builder

import com.krrrr38.mackerel4s.model.Types._
import com.krrrr38.mackerel4s.model.{ Monitor, MonitorIdResponse }
import dispatch.Req
import org.json4s._
import org.json4s.jackson.Serialization

object UpdateMonitorBuilder extends APIBuilder[MonitorID] {
  override val FullPath = (monitorId: MonitorID) => s"/monitors/$monitorId"
  override val MethodVerb: MethodVerb = MethodVerbPut

  def apply(client: (Path) => Req, monitor: Monitor): UpdateMonitorBuilder =
    UpdateMonitorBuilder(baseRequest(client, monitor.id), monitor)
}

private[builder] case class UpdateMonitorBuilder(private val req: Req, monitor: Monitor) extends RequestBuilder[MonitorIdResponse] {
  /**
   * build request with parameters before run http request
   * @return
   */
  override protected def buildRequest: Req =
    req.setBody(Serialization.write(monitor))
}
