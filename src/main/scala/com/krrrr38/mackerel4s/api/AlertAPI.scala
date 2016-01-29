package com.krrrr38.mackerel4s.api

import com.krrrr38.mackerel4s.builder._
import com.krrrr38.mackerel4s.model.Types.AlertID

trait AlertAPI {
  self: MackerelClientBase =>

  /**
   * get alerts
   *
   * @see [[http://help.mackerel.io/entry/spec/api/v0#alert-get]]
   * @return
   */
  def listAlerts = ListAlertsBuilder(client)

  /**
   * close alert
   *
   * @see [[http://help.mackerel.io/entry/spec/api/v0#alert-close]]
   * @param alertId
   * @return
   */
  def closeAlert(alertId: AlertID, reason: String) = CloseAlertBuilder(client, alertId, reason)
}
