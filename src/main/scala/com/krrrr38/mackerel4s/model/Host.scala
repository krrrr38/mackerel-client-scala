package com.krrrr38.mackerel4s
package model

import Types._

object HostStatus {
  def fromString(status: String): Option[HostStatus] = status match {
    case "working" => Some(HostStatusWorking)
    case "standby" => Some(HostStatusStandby)
    case "maintenance" => Some(HostStatusMaintenance)
    case "poweroff" => Some(HostStatusPoweroff)
    case _ => None
  }
}

sealed trait HostStatus {
  override def toString = this match {
    case HostStatusWorking => "working"
    case HostStatusStandby => "standby"
    case HostStatusMaintenance => "maintenance"
    case HostStatusPoweroff => "poweroff"
  }
}

case object HostStatusWorking extends HostStatus
case object HostStatusStandby extends HostStatus
case object HostStatusMaintenance extends HostStatus
case object HostStatusPoweroff extends HostStatus

case class Host(
  id: HostID,
  name: HostName,
  isRetired: Boolean,
  meta: org.json4s.JValue,
  `type`: String,
  status: String,
  memo: String,
  createdAt: Int,
  roles: Map[ServiceName, Seq[RoleName]],
  interfaces: Seq[Interface])

case class Interface(name: String, ipAddress: String, macAddress: String, networkId: Option[String] = None)

case class MetricValue(value: Double, time: Long)
