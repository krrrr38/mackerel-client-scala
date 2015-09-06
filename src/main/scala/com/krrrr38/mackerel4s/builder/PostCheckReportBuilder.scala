package com.krrrr38.mackerel4s.builder

import com.krrrr38.mackerel4s.model.Types.Path
import com.krrrr38.mackerel4s.model.{ CheckReport, SuccessResponse }
import dispatch.Req
import org.json4s.jackson.Serialization

object PostCheckReportBuilder extends APIBuilder[Unit] {
  override val FullPath = (_: Unit) => "/monitoring/checks/report"
  override val MethodVerb = MethodVerbPost

  def apply(client: Path => Req, reports: Seq[CheckReport]): PostCheckReportBuilder =
    PostCheckReportBuilder(baseRequest(client, ()), reports)
}

private[builder] case class PostCheckReportBuilder(private val req: Req, reports: Seq[CheckReport]) extends RequestBuilder[SuccessResponse] {
  /**
   * build request with parameters before run http request
   * @return
   */
  override protected def buildRequest: Req =
    req.setBody(Serialization.write(Map("reports" -> reports)))

  def addReport(report: CheckReport) = this.copy(reports = report +: this.reports)

  def addReports(reports: Seq[CheckReport]) = this.copy(reports = reports ++ this.reports)
}
