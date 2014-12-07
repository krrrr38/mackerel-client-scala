package com.github.krrrr38.mackerel4s
package builder

import dispatch.Req
import org.json4s.{ NoTypeHints, JObject }
import org.json4s.jackson.Serialization

import com.github.krrrr38.mackerel4s.model.{ Interface, HostIdResponse }
import com.github.krrrr38.mackerel4s.model.Types._

object CreateHostBuilder {
  def apply(req: Req, name: HostName): CreateHostBuilder = CreateHostBuilder(req, CreateHostParams(name))
}

private[builder] case class CreateHostParams(
  name: HostName = "",
  meta: JObject = JObject(),
  interfaces: Seq[Interface] = Nil,
  roleFullNames: Seq[RoleFullname] = Nil)

private[builder] case class CreateHostBuilder(private val req: Req, params: CreateHostParams) extends RequestBuilder[HostIdResponse] {
  /**
   * build request with parameters before run http request
   * @return
   */
  override protected def buildRequest: Req = {
    implicit val formats = Serialization.formats(NoTypeHints)
    req.setBody(Serialization.write(params))
  }

  def setName(name: HostName) = this.copy(params = params.copy(name = name))
  def setMeta(meta: JObject) = this.copy(params = params.copy(meta = meta))
  def addInterface(interface: Interface) = this.copy(params = params.copy(interfaces = interface +: params.interfaces))
  def addInterfaces(interfaces: Seq[Interface]) = this.copy(params = params.copy(interfaces = interfaces ++ params.interfaces))

  /**
   * set role name such as "<service-name>:<role-name>".
   * They are constructed by `/^[A-Za-z0-9][A-Za-z0-9_-]+$/`
   * @param fullname
   * @return
   */
  def addRoleFullname(fullname: RoleFullname) = this.copy(params = params.copy(roleFullNames = fullname +: params.roleFullNames))

  /**
   * set role names such as Seq("<service-name>:<role-name>").
   * They are constructed by `/^[A-Za-z0-9][A-Za-z0-9_-]+$/`
   * @param fullnames
   * @return
   */
  def addRoleFullnames(fullnames: Seq[RoleFullname]) = this.copy(params = params.copy(roleFullNames = fullnames ++ params.roleFullNames))
}
