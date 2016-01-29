package com.krrrr38.mackerel4s.model

import com.krrrr38.mackerel4s.model.Types.{ HostID, MonitorID, AlertID }

object AlertStatus {
  def fromString(status: String): Option[AlertStatus] = status match {
    case "OK" => Some(AlertStatusOK)
    case "CRITICAL" => Some(AlertStatusCritical)
    case "WARNING" => Some(AlertStatusWarning)
    case "UNKNOWN" => Some(AlertStatusUnknown)
    case _ => None
  }
}

sealed trait AlertStatus {
  override def toString = this match {
    case AlertStatusOK => "OK"
    case AlertStatusCritical => "CRITICAL"
    case AlertStatusWarning => "WARNING"
    case AlertStatusUnknown => "UNKNOWN"
  }
}

case object AlertStatusOK extends AlertStatus

case object AlertStatusCritical extends AlertStatus

case object AlertStatusWarning extends AlertStatus

case object AlertStatusUnknown extends AlertStatus

case class Alert(
  id: AlertID,
  status: AlertStatus,
  monitorId: MonitorID,
  `type`: MonitorType,
  hostId: Option[HostID],
  value: Option[Long],
  message: Option[String],
  reason: Option[String],
  openedAt: Int,
  closedAt: Option[Int]) extends APIResponse
