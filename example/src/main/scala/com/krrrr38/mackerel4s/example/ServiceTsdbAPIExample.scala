package com.krrrr38.mackerel4s
package example

import java.util.Date

import com.krrrr38.mackerel4s.model.{ SuccessResponse, ServiceMetric }

object PostServiceMetric extends App {
  if (args.length < 3) {
    println(s"[${Console.RED}error${Console.RESET}] apikey could not be found")
    println(s"""[${Console.RED}error${Console.RESET}] usage: `sbt "mackerel-client-scala-example/runMain com.krrrr38.mackerel4s.example.PostServiceMetric apikey servicename metricname"`""")
  } else {
    val apikey = args(0)
    val serviceName = args(1)
    val metricName = args(2)
    val mackerel = new MackerelClient(apikey)

    val value = scala.util.Random.nextInt().abs
    ExampleUtil.showFutureResponse[SuccessResponse](mackerel.postServiceMetric(serviceName, Seq(ServiceMetric(metricName, value, new Date()))).run) { response =>
      if (response.success) {
        println(s"Success to post service metrics: service = $serviceName, metric name = $metricName, value = $value")
      } else {
        println("Failed to post service metrics")
      }
    }
  }
}
