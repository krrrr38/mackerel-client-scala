package com.github.krrrr38.mackerel4s
package api

import com.github.krrrr38.mackerel4s.model.Types._
import com.github.krrrr38.mackerel4s.model.HostMetric
import com.github.krrrr38.mackerel4s.builder.{ LatestTsdbBuilder, PostTsdbBuilder }

trait TsdbAPI {
  self: MackerelClient =>

  /**
   * post host metrics.
   *
   * @see [[http://help-ja.mackerel.io/entry/spec/api/v0#metric-value-post]]
   * @param metrics
   * @return
   */
  def postTsdb(metrics: Seq[HostMetric]) = PostTsdbBuilder[HostMetric](client("/tsdb").POST, metrics)

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
