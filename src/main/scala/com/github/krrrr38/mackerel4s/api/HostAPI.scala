package com.github.krrrr38.mackerel4s
package api

import org.json4s.JObject
import com.github.krrrr38.mackerel4s.builder._
import com.github.krrrr38.mackerel4s.model.{ Interface, HostResponse }
import com.github.krrrr38.mackerel4s.model.Types._

trait HostAPI {
  self: MackerelClient =>

  /**
   * get hosts info.
   *
   * @see [[http://help-ja.mackerel.io/entry/spec/api/v0#hosts-list]]
   * @return
   */
  def listHosts = ListHostsBuilder(client("/hosts.json").GET)

  /**
   * register a new host.
   *
   * @see [[http://help-ja.mackerel.io/entry/spec/api/v0#host-create]]
   * @param name
   * @return
   */
  def createHost(name: HostName) = CreateHostBuilder(client("/hosts").POST, name)

  /**
   * get a host info.
   *
   * @see [[http://help-ja.mackerel.io/entry/spec/api/v0#host-get]]
   * @param hostId
   * @return
   */
  def getHost(hostId: HostID) = SimpleBuilder[HostResponse](client(s"/hosts/$hostId").GET)

  /**
   * update a host info.
   *
   * [NOTICE]
   * name, meta and interfaces are overwritten.
   * but if you set roleFullnames, just add them.
   *
   * @see [[http://help-ja.mackerel.io/entry/spec/api/v0#host-update]]
   * @param hostId
   * @param name
   * @param meta
   * @param interfaces
   * @param roleFullnames
   * @return
   */
  def updateHost(
    hostId: HostID,
    name: HostName,
    meta: JObject = JObject(),
    interfaces: Seq[Interface] = Nil,
    roleFullnames: Seq[RoleFullname] = Nil) = UpdateHostBuilder(client(s"/hosts/$hostId").PUT, name, meta, interfaces, roleFullnames)

  /**
   * update a host status info.
   *
   * @see [[http://help-ja.mackerel.io/entry/spec/api/v0#host-status-update]]
   * @param hostId
   * @param status "standby", "working", "maintenance" or "poweroff"
   * @return
   */
  def updateHostStatus(hostId: HostID, status: HostStatus) = UpdateHostStatusBuilder(client(s"/hosts/$hostId/status").POST, status)

  /**
   * retire a host
   *
   * @see [[http://help-ja.mackerel.io/entry/spec/api/v0#host-retire]]
   * @param hostId
   * @return
   */
  def retireHost(hostId: HostID) = RetireHostBuilder(client(s"/hosts/$hostId/retire").POST)
}
