package com.github.krrrr38.mackerel4s
package example

import java.util.Date

import com.github.krrrr38.mackerel4s.model.{ LatestTsdbResponse, SuccessResponse, HostMetric }

object PostTsdb extends App {
  if (args.length < 3) {
    println(s"[${Console.RED}error${Console.RESET}] apikey could not be found")
    println(s"""[${Console.RED}error${Console.RESET}] usage: `sbt "mackerel-client-scala-example/runMain com.github.krrrr38.mackerel4s.example.PostTsdb apikey hostid metricname"`""")
  } else {
    val apikey = args(0)
    val hostId = args(1)
    val metricName = args(2)
    val mackerel = new Mackerel(apikey)

    val value = scala.util.Random.nextInt().abs
    ExampleUtil.showFutureResponse[SuccessResponse](mackerel.postTsdb(Seq(HostMetric(hostId, metricName, value, new Date()))).run) { response =>
      if (response.success) {
        println(s"Success to post tsdb data: host id = $hostId, metric name = $metricName, value = $value")
      } else {
        println("Failed to post tsdb data")
      }
    }
  }
}

object LatestTsdb extends App {
  if (args.length < 2) {
    println(s"[${Console.RED}error${Console.RESET}] apikey could not be found")
    println(s"""[${Console.RED}error${Console.RESET}] usage: `sbt "mackerel-client-scala-example/runMain com.github.krrrr38.mackerel4s.example.LatestTsdb apikey hostid"`""")
  } else {
    val apikey = args(0)
    val hostId = args(1)
    val mackerel = new Mackerel(apikey)

    ExampleUtil.showFutureResponse[LatestTsdbResponse](mackerel.latestTsdb(Seq(hostId), Seq("loadavg5")).run) { response =>
      println("got following latest tsdb data (only loadavg5)")
      response.tsdbLatest.foreach {
        case (host, data) =>
          println("host : " + host)
          data.foreach {
            case (metricname, metricvalue) =>
              println(s"  metric: $metricname -> " + metricvalue)
          }
      }
    }
  }
}
