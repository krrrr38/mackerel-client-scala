package com.krrrr38.mackerel4s.model

import com.krrrr38.mackerel4s.model.Types._

case class CheckReport(source: ReportSource, name: String, status: CheckReportStatus, message: String, occurredAt: Long)

case class ReportSource(hostId: HostID) {
  val `type`: String = "host"
}

object CheckReportStatus {
  def fromString(status: String): Option[CheckReportStatus] = status match {
    case "OK" => Some(CheckReportStatusOK)
    case "CRITICAL" => Some(CheckReportStatusCritical)
    case "WARNING" => Some(CheckReportStatusWarning)
    case "UNKNOWN" => Some(CheckReportStatusUnknown)
    case _ => None
  }
}

sealed trait CheckReportStatus {
  override def toString = this match {
    case CheckReportStatusOK => "OK"
    case CheckReportStatusCritical => "CRITICAL"
    case CheckReportStatusWarning => "WARNING"
    case CheckReportStatusUnknown => "UNKNOWN"
  }
}

object CheckReportStatusOK extends CheckReportStatus

object CheckReportStatusCritical extends CheckReportStatus

object CheckReportStatusWarning extends CheckReportStatus

object CheckReportStatusUnknown extends CheckReportStatus
