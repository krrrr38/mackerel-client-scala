package com.krrrr38.mackerel4s.api

import com.krrrr38.mackerel4s.builder.PostGraphDefsBuilder
import com.krrrr38.mackerel4s.model.GraphDef

trait GraphDefAPI {
  self: MackerelClientBase =>

  /**
   * create graph def
   *
   * @see [[http://help-ja.mackerel.io/entry/spec/api/v0#http://help-ja.mackerel.io/entry/spec/api/v0#graphdef-post]]
   * @param graphDefs
   * @return
   */
  def createGraphDef(graphDefs: Seq[GraphDef]) = PostGraphDefsBuilder(client, graphDefs)
}
