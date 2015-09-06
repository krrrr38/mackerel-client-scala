package com.krrrr38.mackerel4s.builder

import com.krrrr38.mackerel4s.model.RolesResponse
import com.krrrr38.mackerel4s.model.Types.{ Path, ServiceName }
import dispatch.Req

object ListRolesBuilder extends APIBuilder[ServiceName] {
  override val FullPath = (serviceName: ServiceName) => s"/services/$serviceName/roles"
  override val MethodVerb = MethodVerbGet

  def apply(client: Path => Req, serviceName: ServiceName): ListRolesBuilder =
    ListRolesBuilder(baseRequest(client, serviceName))
}

private[builder] case class ListRolesBuilder(private val req: Req) extends RequestBuilder[RolesResponse] {
  /**
   * build request with parameters before run http request
   * @return
   */
  override protected def buildRequest: Req = req
}
