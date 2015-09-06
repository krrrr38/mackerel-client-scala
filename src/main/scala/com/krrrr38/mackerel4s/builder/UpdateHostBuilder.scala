package com.krrrr38.mackerel4s
package builder

import com.krrrr38.mackerel4s.model.Interface
import com.krrrr38.mackerel4s.model.Types.{ HostID, HostName, Path, RoleFullname }
import dispatch.Req
import org.json4s.JObject

object UpdateHostBuilder extends APIBuilder[HostID] {
  override val FullPath = (hostId: HostID) => s"/hosts/$hostId"
  override val MethodVerb = MethodVerbPut

  def apply(
    client: Path => Req,
    hostId: HostID,
    name: HostName,
    meta: JObject,
    interfaces: Seq[Interface],
    roleFullNames: Seq[RoleFullname]): CreateHostBuilder =
    CreateHostBuilder(baseRequest(client, hostId), CreateHostParams(name, meta, interfaces, roleFullNames))
}
