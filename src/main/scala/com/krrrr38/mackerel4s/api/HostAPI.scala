package com.krrrr38.mackerel4s
package api

import org.json4s.JObject
import com.krrrr38.mackerel4s.builder._
import com.krrrr38.mackerel4s.model.{ HostStatus, Interface }
import com.krrrr38.mackerel4s.model.Types._

trait HostAPI {
  self: MackerelClientBase =>

  /**
   * get hosts info.
   *
   * @see [[http://help-ja.mackerel.io/entry/spec/api/v0#hosts-list]]
   * @return
   */
  def listHosts = ListHostsBuilder(client, None, Nil, None, Nil)

  /**
   * get hosts info.
   *
   * @see [[http://help-ja.mackerel.io/entry/spec/api/v0#hosts-list]]
   * @param statuses host status list
   * @return
   */
  def listHosts(statuses: Seq[HostStatus]) =
    ListHostsBuilder(client, None, Nil, None, statuses)

  /**
   * get hosts info in a service.
   *
   * @see [[http://help-ja.mackerel.io/entry/spec/api/v0#hosts-list]]
   * @param service service name
   * @param roles role name (default Nil)
   * @param statuses host status list (default Nil)
   * @return
   */
  def listHosts(
    service: String,
    roles: Seq[RoleName] = Nil,
    statuses: Seq[HostStatus] = Nil) =
    ListHostsBuilder(client, Some(service), roles, None, statuses)

  /**
   * register a new host.
   *
   * @see [[http://help-ja.mackerel.io/entry/spec/api/v0#host-create]]
   * @param name host name
   * @param roleFullnames set host roles which are constructed lik "<service-name>:<role-name>". (default Nil)
   * @return
   */
  def createHost(name: HostName, roleFullnames: Seq[RoleFullname] = Nil) =
    CreateHostBuilder(client, name, roleFullnames)

  /**
   * get a host info.
   *
   * @see [[http://help-ja.mackerel.io/entry/spec/api/v0#host-get]]
   * @param hostId
   * @return
   */
  def getHost(hostId: HostID) = GetHostBuilder(client, hostId)

  /**
   * update a host info.
   *
   * [NOTICE]
   * name, meta and interfaces are overwritten.
   * but if you set roleFullnames, just add them.
   *
   * @see [[http://help-ja.mackerel.io/entry/spec/api/v0#host-update]]
   * @param hostId target host id
   * @param name update host name
   * @param meta update meta information
   * @param interfaces network interface information
   * @param roleFullnames array of full names of the roles to which this host belongs
   * @return
   */
  def updateHost(
    hostId: HostID,
    name: HostName,
    meta: JObject = JObject(),
    interfaces: Seq[Interface] = Nil,
    roleFullnames: Seq[RoleFullname] = Nil) = UpdateHostBuilder(client, hostId, name, meta, interfaces, roleFullnames)

  /**
   * update a host status info.
   *
   * @see [[http://help-ja.mackerel.io/entry/spec/api/v0#host-status-update]]
   * @param hostId
   * @param status "standby", "working", "maintenance" or "poweroff"
   * @return
   */
  def updateHostStatus(hostId: HostID, status: HostStatus) = UpdateHostStatusBuilder(client, hostId, status)

  /**
   * retire a host
   *
   * @see [[http://help-ja.mackerel.io/entry/spec/api/v0#host-retire]]
   * @param hostId
   * @return
   */
  def retireHost(hostId: HostID) = RetireHostBuilder(client, hostId)
}
