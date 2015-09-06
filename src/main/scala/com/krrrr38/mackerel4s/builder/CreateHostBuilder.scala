package com.krrrr38.mackerel4s
package builder

import dispatch.Req
import org.json4s.{ NoTypeHints, JObject }
import org.json4s.jackson.{ JsonMethods, Serialization }

import com.krrrr38.mackerel4s.model.{ Interface, HostIdResponse }
import com.krrrr38.mackerel4s.model.Types.{ Path, HostName, RoleFullname }

object CreateHostBuilder extends APIBuilder[Unit] {
  override val FullPath = (_: Unit) => "/hosts"
  override val MethodVerb = MethodVerbPost

  def apply(client: Path => Req, name: HostName, roleFullnames: Seq[RoleFullname] = Nil): CreateHostBuilder =
    CreateHostBuilder(baseRequest(client, ()), CreateHostParams(name, roleFullnames = roleFullnames))
}

private[builder] case class CreateHostParams(
  name: HostName = "",
  meta: JObject = JObject(),
  interfaces: Seq[Interface] = Nil,
  roleFullnames: Seq[RoleFullname] = Nil)

private[builder] case class CreateHostBuilder(private val req: Req, params: CreateHostParams) extends RequestBuilder[HostIdResponse] {
  /**
   * build request with parameters before run http request
   * @return
   */
  override protected def buildRequest: Req =
    req.setBody(Serialization.write(params))

  def setName(name: HostName) = this.copy(params = params.copy(name = name))
  def setMeta(meta: JObject) = this.copy(params = params.copy(meta = meta))
  def setMeta(metaJsonObject: String) = {
    val meta = JsonMethods.parse(metaJsonObject).asInstanceOf[JObject]
    this.copy(params = params.copy(meta = meta))
  }
  def addInterface(interface: Interface) = this.copy(params = params.copy(interfaces = interface +: params.interfaces))
  def addInterfaces(interfaces: Seq[Interface]) = this.copy(params = params.copy(interfaces = interfaces ++ params.interfaces))

  /**
   * set role name such as "<service-name>:<role-name>".
   * They are constructed by `/^[A-Za-z0-9][A-Za-z0-9_-]+$/`
   * @param fullname
   * @return
   */
  def addRoleFullname(fullname: RoleFullname) = this.copy(params = params.copy(roleFullnames = fullname +: params.roleFullnames))

  /**
   * set role names such as Seq("<service-name>:<role-name>").
   * They are constructed by `/^[A-Za-z0-9][A-Za-z0-9_-]+$/`
   * @param fullnames
   * @return
   */
  def addRoleFullnames(fullnames: Seq[RoleFullname]) = this.copy(params = params.copy(roleFullnames = fullnames ++ params.roleFullnames))
}
