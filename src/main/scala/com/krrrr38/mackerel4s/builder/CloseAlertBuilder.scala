package com.krrrr38.mackerel4s.builder

import com.krrrr38.mackerel4s.model.Alert
import com.krrrr38.mackerel4s.model.Types.{ AlertID, Path }
import dispatch.Req
import org.json4s.jackson.Serialization

object CloseAlertBuilder extends APIBuilder[AlertID] {
  override val FullPath = (alertId: AlertID) => s"/alerts/$alertId/close"
  override val MethodVerb = MethodVerbPost

  def apply(client: Path => Req, alertId: AlertID, reason: String): CloseAlertBuilder =
    CloseAlertBuilder(baseRequest(client, alertId), CloseAlertParams(reason))
}

private[builder] case class CloseAlertParams(reason: String)

private[builder] case class CloseAlertBuilder(private val req: Req, params: CloseAlertParams) extends RequestBuilder[Alert] {
  /**
   * build request with parameters before run http request
   * @return
   */
  override protected def buildRequest: Req =
    req.setBody(Serialization.write(params))
}
