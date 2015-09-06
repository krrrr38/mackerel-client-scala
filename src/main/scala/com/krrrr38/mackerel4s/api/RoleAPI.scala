package com.krrrr38.mackerel4s.api

import com.krrrr38.mackerel4s.builder.ListRolesBuilder
import com.krrrr38.mackerel4s.model.Types.ServiceName

trait RoleAPI {
  self: MackerelClientBase =>

  /**
   * get roles
   *
   * @see [[http://help-ja.mackerel.io/entry/spec/api/v0#role-list]]
   * @param serviceName
   * @return
   */
  def listRoles(serviceName: ServiceName) = ListRolesBuilder(client, serviceName)
}
