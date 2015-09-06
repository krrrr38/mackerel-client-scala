package com.krrrr38.mackerel4s.api

import com.krrrr38.mackerel4s.builder.ListServicesBuilder

trait ServiceAPI {
  self: MackerelClientBase =>

  /**
   * get services
   *
   * @see [[http://help-ja.mackerel.io/entry/spec/api/v0#service-list]]
   * @return
   */
  def listServices = ListServicesBuilder(client)
}
