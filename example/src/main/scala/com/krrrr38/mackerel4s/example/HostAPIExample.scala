package com.krrrr38.mackerel4s
package example

import com.krrrr38.mackerel4s.model._

object HostList extends App {
  if (args.length < 1) {
    println(s"[${Console.RED}error${Console.RESET}] apikey could not be found")
    println(s"""[${Console.RED}error${Console.RESET}] usage: `sbt "mackerel-client-scala-example/runMain com.krrrr38.mackerel4s.example.HostList apikey"`""")
  } else {
    val apikey = args(0)
    val mackerel = new Mackerel(apikey)

    // filter with service and role
    // then get host list
    ExampleUtil.showFutureResponse[HostsResponse](mackerel.listHosts.setService("test").run) { response =>
      println("There are following hosts in `test` service")
      response.hosts.foreach(host => println(host.name))
    }
  }
}

object CreateHost extends App {
  if (args.length < 2) {
    println(s"[${Console.RED}error${Console.RESET}] apikey could not be found")
    println(s"""[${Console.RED}error${Console.RESET}] usage: `sbt "mackerel-client-scala-example/runMain com.krrrr38.mackerel4s.example.CreateHost apikey hostname"`""")
  } else {
    val apikey = args(0)
    val hostName = args(1)
    val mackerel = new Mackerel(apikey)

    // create host with role name
    ExampleUtil.showFutureResponse[HostIdResponse](mackerel.createHost(hostName).addRoleFullname("test:db").run) { response =>
      println("Register host: host id = " + response.id)
    }
  }
}

object GetHost extends App {
  if (args.length < 2) {
    println(s"[${Console.RED}error${Console.RESET}] apikey or hostid could not be found")
    println(s"""[${Console.RED}error${Console.RESET}] usage: `sbt "mackerel-client-scala-example/runMain com.krrrr38.mackerel4s.example.GetHost apikey hostid"`""")
  } else {
    val apikey = args(0)
    val hostId = args(1)
    val mackerel = new Mackerel(apikey)

    // get host
    ExampleUtil.showFutureResponse[HostResponse](mackerel.getHost(hostId).run) { response =>
      println("Register host: host id = " + response.host.name)
    }
  }
}

object UpdateHost extends App {
  if (args.length < 3) {
    println(s"[${Console.RED}error${Console.RESET}] apikey, hostid or name could not be found")
    println(s"""[${Console.RED}error${Console.RESET}] usage: `sbt "mackerel-client-scala-example/runMain com.krrrr38.mackerel4s.example.UpdateHost apikey hostid newname"`""")
  } else {
    val apikey = args(0)
    val hostId = args(1)
    val newName = args(2)
    val mackerel = new Mackerel(apikey)

    ExampleUtil.showFutureResponse[HostIdResponse](mackerel.updateHost(hostId, newName).run) { response =>
      println("Success to update host name for " + hostId)
    }
  }
}

object UpdateHostStatus extends App {
  if (args.length < 3) {
    println(s"[${Console.RED}error${Console.RESET}] apikey, hostid or status could not be found")
    println(s"""[${Console.RED}error${Console.RESET}] usage: `sbt "mackerel-client-scala-example/runMain com.krrrr38.mackerel4s.example.UpdateHostStatus apikey hostid newstatus"`""")
  } else {
    val apikey = args(0)
    val hostId = args(1)
    val newStatus = args(2)
    val mackerel = new Mackerel(apikey)

    // update host status
    ExampleUtil.showFutureResponse[SuccessResponse](mackerel.updateHostStatus(hostId, newStatus).run) { response =>
      if (response.success) {
        println("Success to update Host Status: host id = " + hostId + ", new status = " + newStatus)
      } else {
        println("Failed to update Host Status: host id = " + hostId)
      }
    }
  }
}

object RetireHost extends App {
  if (args.length < 2) {
    println(s"[${Console.RED}error${Console.RESET}] apikey or hostid could not be found")
    println(s"""[${Console.RED}error${Console.RESET}] usage: `sbt "mackerel-client-scala-example/runMain com.krrrr38.mackerel4s.example.RetireHost apikey hostid"`""")
  } else {
    val apikey = args(0)
    val hostId = args(1)
    val mackerel = new Mackerel(apikey)

    // update host status
    ExampleUtil.showFutureResponse[SuccessResponse](mackerel.retireHost(hostId).run) { response =>
      if (response.success) {
        println("Success to retire host: host id = " + hostId)
      } else {
        println("Failed to update host: host id = " + hostId)
      }
    }
  }
}
