package com.krrrr38.mackerel4s.model

import com.krrrr38.mackerel4s.model.Types.{ MonitorID, ServiceName }

object MonitorOperator {
  def fromString(operator: String): Option[MonitorOperator] = operator match {
    case "<" => Some(MonitorOperatorLT)
    case ">" => Some(MonitorOperatorGT)
    case _ => None
  }
}

sealed trait MonitorOperator {
  override def toString = this match {
    case MonitorOperatorLT => "<"
    case MonitorOperatorGT => ">"
  }
}

case object MonitorOperatorLT extends MonitorOperator

case object MonitorOperatorGT extends MonitorOperator

object MonitorType {
  def fromString(monitorType: String): Option[MonitorType] = monitorType match {
    case "connectivity" => Some(MonitorTypeConnectivity)
    case "host" => Some(MonitorTypeHost)
    case "service" => Some(MonitorTypeService)
    case "external" => Some(MonitorTypeExternal)
    case _ => None
  }
}

sealed trait MonitorType {
  override def toString = this match {
    case MonitorTypeConnectivity => "connectivity"
    case MonitorTypeHost => "host"
    case MonitorTypeService => "service"
    case MonitorTypeExternal => "external"
  }
}

case object MonitorTypeConnectivity extends MonitorType

case object MonitorTypeHost extends MonitorType

case object MonitorTypeService extends MonitorType

case object MonitorTypeExternal extends MonitorType

sealed trait Monitor extends APIResponse {
  val `type`: MonitorType
  val id: MonitorID
}

case class ConnectivityMonitor(id: MonitorID, scopes: Seq[String], excludeScopes: Seq[String]) extends Monitor {
  override val `type`: MonitorType = MonitorTypeConnectivity
}

case class HostMonitor(id: MonitorID, name: String, duration: Int, metric: String, operator: MonitorOperator, warning: Long, critical: Long, scopes: Seq[String], excludeScopes: Seq[String]) extends Monitor {
  override val `type`: MonitorType = MonitorTypeHost
}

case class ServiceMonitor(id: MonitorID, name: String, service: ServiceName, duration: Int, metric: String, operator: MonitorOperator, warning: Long, critical: Long) extends Monitor {
  override val `type`: MonitorType = MonitorTypeService
}

case class ExternalMonitor(id: MonitorID, name: String, url: String, service: ServiceName) extends Monitor {
  override val `type`: MonitorType = MonitorTypeExternal
}