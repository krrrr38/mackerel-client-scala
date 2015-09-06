package com.krrrr38.mackerel4s.builder

import com.krrrr38.mackerel4s.model.ServicesResponse
import com.krrrr38.mackerel4s.model.Types.Path
import dispatch.Req

object ListServicesBuilder extends APIBuilder[Unit] {
  override val FullPath = (_: Unit) => "/services"
  override val MethodVerb = MethodVerbGet

  def apply(client: Path => Req): ListServicesBuilder =
    ListServicesBuilder(baseRequest(client, ()))
}

private[builder] case class ListServicesBuilder(private val req: Req) extends RequestBuilder[ServicesResponse] {
  /**
   * build request with parameters before run http request
   * @return
   */
  override protected def buildRequest: Req = req
}
