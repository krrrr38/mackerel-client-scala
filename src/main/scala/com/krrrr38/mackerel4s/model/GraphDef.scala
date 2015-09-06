package com.krrrr38.mackerel4s
package model

import Types._

case class GraphDef(graphs: Map[GraphName, Graph])

/**
 * @param label the display name for user-defined metrics {graph}.* Dots (.) can be included in {graph} .
 * @param unit the value type for user-defined metrics
 * @param metrics the array of metric definitions for user-defined metrics {graph}.*
 */
case class Graph(label: GraphLabel, unit: GraphUnitType, metrics: Seq[GraphMetric])

/**
 * @param name this metric expresses the handling of user-defined metrics {graph}.{name}. Dots (.) cannot be included in this value. Characters that can be used in the metric’s name are alphanumeric characters, hyphens, and underscores (/[-a-zA-Z0-9_]/) .
 * @param label the display name of the time series for user-defined metrics {graph}.{name}
 * @param stacked indicates whether or not the time series for user-defined metrics {graph}.{name} is stacked. if false, it will be displayed as a line.
 */
case class GraphMetric(name: MetricName, label: MetricLabel, stacked: Boolean)

object GraphUnitType {
  def fromString(unit: String): Option[GraphUnitType] = unit match {
    case "float" => Some(GraphUnitTypeFloat)
    case "integer" => Some(GraphUnitTypeInteger)
    case "percentage" => Some(GraphUnitTypePercentage)
    case "bytes" => Some(GraphUnitTypeBytes)
    case "bytes/sec" => Some(GraphUnitTypeBytesSec)
    case "iops" => Some(GraphUnitTypeIOPS)
    case _ => None
  }
}

sealed trait GraphUnitType {
  override def toString = this match {
    case GraphUnitTypeFloat => "float"
    case GraphUnitTypeInteger => "integer"
    case GraphUnitTypePercentage => "percentage"
    case GraphUnitTypeBytes => "bytes"
    case GraphUnitTypeBytesSec => "bytes/sec"
    case GraphUnitTypeIOPS => "iops"
  }
}

object GraphUnitTypeFloat extends GraphUnitType
object GraphUnitTypeInteger extends GraphUnitType
object GraphUnitTypePercentage extends GraphUnitType
object GraphUnitTypeBytes extends GraphUnitType
object GraphUnitTypeBytesSec extends GraphUnitType
object GraphUnitTypeIOPS extends GraphUnitType

