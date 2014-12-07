package com.krrrr38.mackerel4s
package model

import Types._

trait APIResponse

case class HostsResponse(hosts: Seq[Host]) extends APIResponse

case class HostIdResponse(id: HostID) extends APIResponse

case class HostResponse(host: Host) extends APIResponse

case class LatestTsdbResponse(tsdbLatest: Map[HostName, Map[MetricName, MetricValue]]) extends APIResponse

case class SuccessResponse(success: Boolean) extends APIResponse
