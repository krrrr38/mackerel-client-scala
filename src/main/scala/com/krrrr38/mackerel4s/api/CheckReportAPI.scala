package com.krrrr38.mackerel4s.api

import com.krrrr38.mackerel4s.builder.PostCheckReportBuilder
import com.krrrr38.mackerel4s.model.CheckReport

trait CheckReportAPI {
  self: MackerelClientBase =>

  /**
   * post check monitoring report
   *
   * @see [[http://help-ja.mackerel.io/entry/spec/api/v0#monitoring-check-report-post]]
   * @param reports
   * @return
   */
  def postCheckReports(reports: Seq[CheckReport]) =
    PostCheckReportBuilder(client, reports)
}
