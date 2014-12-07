package com.krrrr38.mackerel4s
package builder

import dispatch.Req
import org.json4s.JObject

import com.krrrr38.mackerel4s.model.Interface
import com.krrrr38.mackerel4s.model.Types._

object UpdateHostBuilder {
  def apply(
    req: Req,
    name: HostName,
    meta: JObject,
    interfaces: Seq[Interface],
    roleFullNames: Seq[RoleFullname]): CreateHostBuilder =
    CreateHostBuilder(req, CreateHostParams(name, meta, interfaces, roleFullNames))
}
