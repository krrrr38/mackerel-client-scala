package com.krrrr38.mackerel4s
package api

import com.krrrr38.mackerel4s.model.Types.ServiceName
import com.krrrr38.mackerel4s.model.ServiceMetric
import com.krrrr38.mackerel4s.builder.PostServiceTsdbBuilder

trait ServiceMetricAPI {
  self: MackerelClientBase =>

  /**
   * post service metrics.
   *
   * @see [[http://help-ja.mackerel.io/entry/spec/api/v0#service-metric-value-post]]
   * @param serviceName
   * @param metrics
   * @return
   */
  @deprecated("Use method postServiceMetrics instead.", "0.4.0")
  def postServiceMetric(serviceName: ServiceName, metrics: Seq[ServiceMetric]) =
    postServiceMetrics(serviceName, metrics)

  /**
   * post service metrics.
   *
   * @see [[http://help-ja.mackerel.io/entry/spec/api/v0#service-metric-value-post]]
   * @param serviceName
   * @param metrics
   * @return
   */
  def postServiceMetrics(serviceName: ServiceName, metrics: Seq[ServiceMetric]) =
    PostServiceTsdbBuilder(client, serviceName, metrics)
}
