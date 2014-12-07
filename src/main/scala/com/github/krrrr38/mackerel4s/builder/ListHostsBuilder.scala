package com.github.krrrr38.mackerel4s
package builder

import dispatch.Req

import com.github.krrrr38.mackerel4s.model.HostsResponse
import com.github.krrrr38.mackerel4s.model.Types._

object ListHostsBuilder {
  def apply(req: Req): ListHostsBuilder = ListHostsBuilder(req, ListHostsParams())
}

private[builder] case class ListHostsParams(
  service: Option[ServiceName] = None,
  roles: Seq[RoleName] = Nil,
  name: Option[HostName] = None,
  statuses: Seq[HostStatus] = Nil)

private[builder] case class ListHostsBuilder(private val req: Req, params: ListHostsParams) extends RequestBuilder[HostsResponse] {
  object Params {
    val SERVICE = "service"
    val ROLE = "role"
    val NAME = "name"
    val STATUS = "status"
  }

  /**
   * build request with parameters before run http request
   * @return
   */
  override protected def buildRequest: Req = {
    val parameters = params.service.map(Params.SERVICE -> _).toSeq ++
      params.roles.map(Params.ROLE -> _) ++
      params.name.map(Params.NAME -> _).toSeq ++
      params.statuses.map(Params.STATUS -> _)
    parameters.foldLeft(req) {
      case (r, (key, value)) =>
        r.addQueryParameter(key, value)
    }
  }

  def setService(service: ServiceName) = this.copy(params = params.copy(service = Some(service)))
  def addRole(role: RoleName) = this.copy(params = params.copy(roles = role +: params.roles))
  def addRoles(roles: Seq[RoleName]) = this.copy(params = params.copy(roles = roles ++ params.roles))
  def setName(name: HostName) = this.copy(params = params.copy(name = Some(name)))
  def addStatus(status: HostStatus) = this.copy(params = params.copy(statuses = status +: params.statuses))
  def addStatuses(statuses: Seq[HostStatus]) = this.copy(params = params.copy(statuses = statuses ++ params.statuses))
}
