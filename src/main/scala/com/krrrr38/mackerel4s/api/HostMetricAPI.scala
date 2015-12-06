package com.krrrr38.mackerel4s
package api

import com.krrrr38.mackerel4s.model.Types.{ HostID, MetricName }
import com.krrrr38.mackerel4s.model.HostMetric
import com.krrrr38.mackerel4s.builder.{ LatestHostTsdbBuilder, PostHostTsdbBuilder }

trait HostMetricAPI {
  self: MackerelClientBase =>

  /**
   * post host metrics.
   *
   * @see [[http://help-ja.mackerel.io/entry/spec/api/v0#metric-value-post]]
   * @param metrics
   * @return
   */
  @deprecated("Use method postHostMetrics instead.", "0.4.0")
  def postTsdb(metrics: Seq[HostMetric]) = postHostMetircs(metrics)

  /**
   * post host metrics.
   *
   * @see [[http://help-ja.mackerel.io/entry/spec/api/v0#metric-value-post]]
   * @param metrics
   * @return
   */
  def postHostMetircs(metrics: Seq[HostMetric]) = PostHostTsdbBuilder(client, metrics)

  /**
   * get latest host metrics.
   *
   * @see [[http://help-ja.mackerel.io/entry/spec/api/v0#tsdb-latest]]
   * @param hostIds
   * @param names
   * @return
   */
  @deprecated("Use method getLatestHostMetrics instead.", "0.4.0")
  def latestTsdb(hostIds: Seq[HostID] = Nil, names: Seq[MetricName] = Nil) = getLatestHostMetrics(hostIds, names)

  /**
   * get latest host metrics.
   *
   * @see [[http://help-ja.mackerel.io/entry/spec/api/v0#tsdb-latest]]
   * @param hostIds
   * @param names
   * @return
   */
  def getLatestHostMetrics(hostIds: Seq[HostID] = Nil, names: Seq[MetricName] = Nil) = LatestHostTsdbBuilder(client("/tsdb/latest").GET, hostIds, names)
}
