package com.krrrr38.mackerel4s
package model

import Types._

trait APIResponse

case class ServicesResponse(services: Seq[Service]) extends APIResponse

case class RolesResponse(roles: Seq[Role]) extends APIResponse

case class HostsResponse(hosts: Seq[Host]) extends APIResponse

case class HostIdResponse(id: HostID) extends APIResponse

case class HostResponse(host: Host) extends APIResponse

case class MonitorIdResponse(id: MonitorID) extends APIResponse

case class MonitorsResponse(monitors: Seq[Monitor]) extends APIResponse

case class AlertsResponse(alerts: Seq[Alert]) extends APIResponse

case class LatestTsdbResponse(tsdbLatest: Map[HostName, Map[MetricName, MetricValue]]) extends APIResponse

case class SuccessResponse(success: Boolean) extends APIResponse
