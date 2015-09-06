package com.krrrr38.mackerel4s.builder

import com.krrrr38.mackerel4s.model.Types._
import com.krrrr38.mackerel4s.model.{ Monitor, MonitorIdResponse }
import dispatch.Req
import org.json4s._
import org.json4s.jackson.Serialization

object CreateMonitorBuilder extends APIBuilder[Unit] {
  override val FullPath = (_: Unit) => "/monitors"
  override val MethodVerb: MethodVerb = MethodVerbPost

  def apply[RESULT <: Monitor](client: (Path) => Req, monitor: Monitor): CreateMonitorBuilder[RESULT] =
    CreateMonitorBuilder[RESULT](baseRequest(client, ()), monitor)
}

private[builder] case class CreateMonitorBuilder[RESULT <: Monitor](private val req: Req, monitor: Monitor) extends RequestBuilder[RESULT] {
  /**
   * build request with parameters before run http request
   * @return
   */
  override protected def buildRequest: Req =
    req.setBody(Serialization.write(monitor))
}
