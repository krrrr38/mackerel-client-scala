package com.krrrr38.mackerel4s
package builder

import dispatch.Req

import com.krrrr38.mackerel4s.model.LatestTsdbResponse
import com.krrrr38.mackerel4s.model.Types.{ Path, HostID, MetricName }

object LatestTsdbBuilder extends APIBuilder[Unit] {
  override val FullPath = (_: Unit) => "/tsdb/latest"
  override val MethodVerb: MethodVerb = MethodVerbGet

  def apply(client: Path => Req, hostIds: Seq[HostID], names: Seq[MetricName]): LatestTsdbBuilder =
    LatestTsdbBuilder(baseRequest(client, ()), hostIds, names)
}

private[builder] case class LatestTsdbBuilder(private val req: Req, hostIds: Seq[HostID], names: Seq[MetricName]) extends RequestBuilder[LatestTsdbResponse] {
  object Params {
    val HOST_ID = "hostId"
    val NAME = "name"
  }

  /**
   * build request with parameters before run http request
   * @return
   */
  override protected def buildRequest: Req = {
    val parameters = hostIds.map(Params.HOST_ID -> _) ++ names.map(Params.NAME -> _)
    parameters.foldLeft(req) {
      case (r, (key, value)) =>
        r.addQueryParameter(key, value)
    }
  }

  def addHostId(hostId: HostID) = this.copy(hostIds = hostId +: hostIds)
  def addHostIds(hostIds: Seq[HostID]) = this.copy(hostIds = hostIds ++ this.hostIds)
  def addName(name: MetricName) = this.copy(names = name +: names)
  def addNames(names: Seq[MetricName]) = this.copy(names = names ++ this.names)
}
