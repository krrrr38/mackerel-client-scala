package com.krrrr38.mackerel4s.api

import javax.servlet.http.{ HttpServletResponse, HttpServletRequest }

import org.eclipse.jetty.server.{ Request, Server }
import org.eclipse.jetty.server.handler.AbstractHandler

import org.scalatest.{ FunSpec, BeforeAndAfter }

object MockApiServer {
  lazy val port = {
    // find free port
    val socket = new java.net.ServerSocket(0)
    val port = socket.getLocalPort
    socket.close()
    port
  }
  lazy val url = s"http://localhost:$port"

  lazy val server = new Server(port)
}

trait MockApiServerFun extends FunSpec with BeforeAndAfter {
  import scala.io.Source

  before {
    // `test/resources/api/[GET|POST]` is root for request
    val handler = new AbstractHandler {
      override def handle(target: String, baseRequest: Request, request: HttpServletRequest, response: HttpServletResponse): Unit = {
        val path = baseRequest.getPathInfo
        val source = Source.fromURL(getClass.getClassLoader.getResource(s"api/${baseRequest.getMethod}$path.json"))
        response.setContentType("application/json;charset=utf-8")
        response.setStatus(HttpServletResponse.SC_OK)
        baseRequest.setHandled(true)
        response.getWriter.print(source.getLines().mkString("\n"))
      }
    }

    MockApiServer.server.setHandler(handler)
    MockApiServer.server.start()
  }

  after {
    MockApiServer.server.stop()
  }
}
