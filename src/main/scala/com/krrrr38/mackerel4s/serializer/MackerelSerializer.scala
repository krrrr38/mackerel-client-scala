package com.krrrr38.mackerel4s.serializer

import com.krrrr38.mackerel4s.model._
import org.json4s.JsonAST.JString
import org.json4s._
import org.json4s.jackson.Serialization

object MackerelSerializer {
  val FORMATS = Serialization.formats(NoTypeHints) +
    MonitorSerializer +
    FieldSerializer[Monitor]() +
    MonitorTypeSerializer +
    MonitorOperatorSerializer +
    HostStatusSerializer +
    AlertStatusSerializer +
    CheckReportStatusSerializer +
    GraphUnitTypeSerializer
}

object MonitorSerializer extends CustomSerializer[Monitor](implicit formats =>
  ({
    case jobject: JObject => jobject \ "type" match {
      case JString(typ) => MonitorType.fromString(typ) match {
        case Some(MonitorTypeConnectivity) => jobject.extract[ConnectivityMonitor]
        case Some(MonitorTypeHost) => jobject.extract[HostMonitor]
        case Some(MonitorTypeService) => jobject.extract[ServiceMonitor]
        case Some(MonitorTypeExternal) => jobject.extract[ExternalMonitor]
        case _ => throw new MackerelClientException("Failed to parse monitor object: " + jobject)
      }
      case jvalue => throw new MackerelClientException("Failed to parse monitor object: " + jvalue)
    }
    case jvalue => throw new MackerelClientException("Failed to parse monitor object: " + jvalue)
  }, {
    case monitor: Monitor =>
      val formatter = Serialization.formats(NoTypeHints) + MonitorTypeSerializer + FieldSerializer[Monitor]() + MonitorOperatorSerializer
      jackson.JsonMethods.parse(Serialization.write(monitor)(formatter))
  })
)

object MonitorTypeSerializer extends CustomSerializer[MonitorType](formats =>
  ({
    case JString(typ) => MonitorType.fromString(typ).getOrElse(throw new MackerelClientException("Failed to parse monitor type: " + typ))
  }, {
    case monitorType: MonitorType => JString(monitorType.toString)
  })
)

object MonitorOperatorSerializer extends CustomSerializer[MonitorOperator](formats =>
  ({
    case JString(operator) => MonitorOperator.fromString(operator).getOrElse(throw new MackerelClientException("Failed to parse monitor operator: " + operator))
  }, {
    case monitorOperator: MonitorOperator => JString(monitorOperator.toString)
  })
)

object HostStatusSerializer extends CustomSerializer[HostStatus](formats =>
  ({
    case JString(status) => HostStatus.fromString(status).getOrElse(throw new MackerelClientException("Failed to parse host status: " + status))
  }, {
    case hostStatus: HostStatus => JString(hostStatus.toString)
  })
)

object AlertStatusSerializer extends CustomSerializer[AlertStatus](formats =>
  ({
    case JString(status) => AlertStatus.fromString(status).getOrElse(throw new MackerelClientException("Failed to parse host status: " + status))
  }, {
    case alertStatus: AlertStatus => JString(alertStatus.toString)
  })
)

object CheckReportStatusSerializer extends CustomSerializer[CheckReportStatus](formats =>
  ({
    case JString(status) => CheckReportStatus.fromString(status).getOrElse(throw new MackerelClientException("Failed to parse check report status: " + status))
  }, {
    case checkReportStatus: CheckReportStatus => JString(checkReportStatus.toString)
  })
)

object GraphUnitTypeSerializer extends CustomSerializer[GraphUnitType](formats =>
  ({
    case JString(typ) => GraphUnitType.fromString(typ).getOrElse(throw new MackerelClientException("Failed to parse graph unit type: " + typ))
  }, {
    case typ: GraphUnitType => JString(typ.toString)
  })
)
