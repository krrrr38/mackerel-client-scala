package com.github.krrrr38.mackerel4s
package model

import java.util.Date

import Types._

trait TsdbMetric

/**
 * host tsdb data
 * @param hostId
 * @param name data name
 * @param value data value
 * @param time epoch second
 */
case class HostMetric(hostId: HostID, name: MetricName, value: Double, time: Long) extends TsdbMetric

object HostMetric {
  /**
   * host tsdb data
   * @param hostId
   * @param name data name
   * @param value data value
   * @param time
   * @return
   */
  def apply(hostId: HostID, name: MetricName, value: Double, time: Date): HostMetric =
    apply(hostId, name, value, time.getTime / 1000)
}

/**
 * service metric data
 * @param name data name
 * @param value data value
 * @param time epoch second
 */
case class ServiceMetric(name: MetricName, value: Double, time: Long) extends TsdbMetric

object ServiceMetric {
  /**
   * service metric data
   * @param name data name
   * @param value data value
   * @param time
   * @return
   */
  def apply(name: MetricName, value: Double, time: Date): ServiceMetric =
    apply(name, value, time.getTime / 1000)
}
