package com.krrrr38.mackerel4s.api

import com.krrrr38.mackerel4s.builder.{ CreateMonitorBuilder, DeleteMonitorBuilder, ListMonitorsBuilder, UpdateMonitorBuilder }
import com.krrrr38.mackerel4s.model.Types.{ MonitorID, ServiceName }
import com.krrrr38.mackerel4s.model._

trait MonitorAPI {
  self: MackerelClientBase =>

  /**
   * get monitors
   *
   * @see [[http://help-ja.mackerel.io/entry/spec/api/v0#monitor-get]]
   * @return
   */
  def listMonitors = ListMonitorsBuilder(client)

  /**
   * create new connectivity monitor
   * @param scopes monitoring target’s service name or role details name
   * @param excludeScopes monitoring exclusion target’s service name or role details name
   * @see [[http://help-ja.mackerel.io/entry/spec/api/v0#monitor-create]]
   * @return
   */
  def createConnectivityMonitor(scopes: Seq[String] = Nil, excludeScopes: Seq[String] = Nil) =
    CreateMonitorBuilder[ConnectivityMonitor](client, ConnectivityMonitor(null, scopes, excludeScopes))

  /**
   * create new host monitor
   * @param name arbitrary name that can be seen in the list of monitors and elsewhere
   * @param duration average value of the designated interval (in minutes) will be monitored. valid interval (1 to 5 min.)
   * @param metric name of the host metric targeted by monitoring. by designating a specific constant string, comparative monitoring is possible
   * @param operator determines the conditions that state whether the designated variable is either big or small. the observed value is on the left of ”>” or ”<” and the designated value is on the right
   * @param warning the threshold that generates a warning alert
   * @param critical the threshold that generates a critical alert
   * @param scopes monitoring target’s service name or role details name
   * @param excludeScopes monitoring exclusion target’s service name or role details name
   * @see [[http://help-ja.mackerel.io/entry/spec/api/v0#monitor-create]]
   * @return
   */
  def createHostMonitor(name: String, duration: Int, metric: String, operator: MonitorOperator, warning: Long, critical: Long, scopes: Seq[String] = Nil, excludeScopes: Seq[String] = Nil) =
    CreateMonitorBuilder[HostMonitor](client, HostMonitor(null, name, duration, metric, operator, warning, critical, scopes, excludeScopes))

  /**
   * create new service monitor
   * @param name arbitrary name that can be seen in the list of monitors and elsewhere
   * @param serviceName name of the service targeted by monitoring
   * @param duration average value of the designated interval (in minutes) will be monitored. valid interval (1 to 5 min.)
   * @param metric name of the host metric targeted by monitoring. by designating a specific constant string, comparative monitoring is possible
   * @param operator determines the conditions that state whether the designated variable is either big or small. the observed value is on the left of ”>” or ”<” and the designated value is on the right
   * @param warning the threshold that generates a warning alert
   * @param critical the threshold that generates a critical alert
   * @see [[http://help-ja.mackerel.io/entry/spec/api/v0#monitor-create]]
   * @return
   */
  def createServiceMonitor(name: String, serviceName: ServiceName, duration: Int, metric: String, operator: MonitorOperator, warning: Long, critical: Long) =
    CreateMonitorBuilder[ServiceMonitor](client, ServiceMonitor(null, name, serviceName, duration, metric, operator, warning, critical))

  /**
   * create new external monitor
   * @param name arbitrary name that can refer to monitors list, etc.
   * @param url monitoring target URL
   * @param serviceName name of the service targeted by monitoring
   * @see [[http://help-ja.mackerel.io/entry/spec/api/v0#monitor-create]]
   * @return
   */
  def createExternalMonitor(name: String, url: String, serviceName: ServiceName) =
    CreateMonitorBuilder[ExternalMonitor](client, ExternalMonitor(null, name, url, serviceName))

  /**
   * update monitor
   *
   * @see [[http://help-ja.mackerel.io/entry/spec/api/v0#monitor-update]]
   * @param monitor
   * @return
   */
  def updateMonitor(monitor: Monitor) = UpdateMonitorBuilder(client, monitor)

  /**
   * delete monitor
   *
   * @see [[http://help-ja.mackerel.io/entry/spec/api/v0#monitor-delete]]
   * @param monitorId
   * @return
   */
  def deleteMonitor(monitorId: MonitorID) = DeleteMonitorBuilder(client, monitorId)
}
