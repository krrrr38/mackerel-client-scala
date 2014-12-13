package com.krrrr38.mackerel4s
package api

import com.krrrr38.mackerel4s.model.Types.{ HostID, MetricName }
import com.krrrr38.mackerel4s.model.HostMetric
import com.krrrr38.mackerel4s.builder.{ LatestTsdbBuilder, PostTsdbBuilder }

trait TsdbAPI {
  self: MackerelClientBase =>

  /**
   * post host metrics.
   *
   * @see [[http://help-ja.mackerel.io/entry/spec/api/v0#metric-value-post]]
   * @param metrics
   * @return
   */
  def postTsdb(metrics: Seq[HostMetric]) = PostTsdbBuilder(client, metrics)

  /**
   * get latest host metrics.
   *
   * @see [[http://help-ja.mackerel.io/entry/spec/api/v0#tsdb-latest]]
   * @param hostIds
   * @param names
   * @return
   */
  def latestTsdb(hostIds: Seq[HostID] = Nil, names: Seq[MetricName] = Nil) = LatestTsdbBuilder(client("/tsdb/latest").GET, hostIds, names)
}
