package com.github.krrrr38.mackerel4s
package api

import com.github.krrrr38.mackerel4s.model.Types._
import com.github.krrrr38.mackerel4s.model.ServiceMetric
import com.github.krrrr38.mackerel4s.builder.PostTsdbBuilder

trait ServiceTsdbAPI {
  self: MackerelClient =>

  /**
   * post service metrics.
   *
   * @see [[http://help-ja.mackerel.io/entry/spec/api/v0#service-metric-value-post]]
   * @param serviceName
   * @param metrics
   * @return
   */
  def postServiceMetric(serviceName: ServiceName, metrics: Seq[ServiceMetric]) =
    PostTsdbBuilder[ServiceMetric](client(s"/services/$serviceName/tsdb").POST, metrics)
}
