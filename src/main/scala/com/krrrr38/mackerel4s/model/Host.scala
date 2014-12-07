package com.krrrr38.mackerel4s
package model

import Types._

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
