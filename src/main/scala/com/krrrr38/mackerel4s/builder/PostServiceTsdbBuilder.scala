package com.krrrr38.mackerel4s.builder

import dispatch.Req
import org.json4s.NoTypeHints
import org.json4s.jackson.Serialization

import com.krrrr38.mackerel4s.model.{ ServiceMetric, SuccessResponse }
import com.krrrr38.mackerel4s.model.Types.{ ServiceName, Path }

object PostServiceTsdbBuilder extends APIBuilder[ServiceName] {
  override val FullPath = (serviceName: ServiceName) => s"/services/$serviceName/tsdb"
  override val MethodVerb = MethodVerbPost

  def apply(client: Path => Req, serviceName: ServiceName, metrics: Seq[ServiceMetric]): PostServiceTsdbBuilder =
    PostServiceTsdbBuilder(baseRequest(client, serviceName), metrics)
}

private[builder] case class PostServiceTsdbBuilder(private val req: Req, metrics: Seq[ServiceMetric]) extends RequestBuilder[SuccessResponse] {
  /**
   * build request with parameters before run http request
   * @return
   */
  override protected def buildRequest: Req = {
    implicit val formats = Serialization.formats(NoTypeHints)
    req.setBody(Serialization.write(metrics))
  }

  def addMetric(metric: ServiceMetric) = this.copy(metrics = metric +: this.metrics)
  def addMetrics(metrics: Seq[ServiceMetric]) = this.copy(metrics = metrics ++ this.metrics)
}
