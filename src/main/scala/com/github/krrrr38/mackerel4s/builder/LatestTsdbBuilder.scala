package com.github.krrrr38.mackerel4s
package builder

import com.github.krrrr38.mackerel4s.model.Types._
import com.github.krrrr38.mackerel4s.model.LatestTsdbResponse
import dispatch.Req

case class LatestTsdbBuilder(private val req: Req, hostIds: Seq[HostID], names: Seq[MetricName]) extends RequestBuilder[LatestTsdbResponse] {
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
