package com.krrrr38.mackerel4s
package builder

import dispatch.Req
import org.json4s.NoTypeHints
import org.json4s.jackson.Serialization

import com.krrrr38.mackerel4s.model.{ HostMetric, SuccessResponse }
import com.krrrr38.mackerel4s.model.Types.Path

object PostTsdbBuilder extends APIBuilder[Unit] {
  override val FullPath = (_: Unit) => "/tsdb"
  override val MethodVerb = MethodVerbPost

  def apply(client: Path => Req, metrics: Seq[HostMetric]): PostTsdbBuilder =
    PostTsdbBuilder(baseRequest(client, ()), metrics)
}

private[builder] case class PostTsdbBuilder(private val req: Req, metrics: Seq[HostMetric]) extends RequestBuilder[SuccessResponse] {
  /**
   * build request with parameters before run http request
   * @return
   */
  override protected def buildRequest: Req =
    req.setBody(Serialization.write(metrics))

  def addMetric(metric: HostMetric) = this.copy(metrics = metric +: this.metrics)
  def addMetrics(metrics: Seq[HostMetric]) = this.copy(metrics = metrics ++ this.metrics)
}
