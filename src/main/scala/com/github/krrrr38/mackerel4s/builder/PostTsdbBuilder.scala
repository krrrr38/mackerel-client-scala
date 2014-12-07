package com.github.krrrr38.mackerel4s
package builder

import dispatch.Req
import org.json4s.NoTypeHints
import org.json4s.jackson.Serialization

import com.github.krrrr38.mackerel4s.model.{ TsdbMetric, SuccessResponse }

case class PostTsdbBuilder[A <: TsdbMetric](private val req: Req, metrics: Seq[A]) extends RequestBuilder[SuccessResponse] {
  /**
   * build request with parameters before run http request
   * @return
   */
  override protected def buildRequest: Req = {
    implicit val formats = Serialization.formats(NoTypeHints)
    req.setBody(Serialization.write(metrics))
  }

  def addMetric(metric: A) = this.copy(metrics = metric +: this.metrics)
  def addMetrics(metrics: Seq[A]) = this.copy(metrics = metrics ++ this.metrics)
}
