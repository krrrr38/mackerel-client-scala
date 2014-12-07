package com.github.krrrr38.mackerel4s
package example

import scala.util._
import scala.concurrent.Future
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.concurrent.ExecutionContext.Implicits.global

import com.github.krrrr38.mackerel4s.builder.MackerelResponseException

object ExampleUtil {
  def showFutureResponse[A](f: Future[A])(success: A => Unit) = {
    f onComplete {
      case Success(response) => success(response)
      case Failure(ex: MackerelResponseException) =>
        println("An error has occured: " + ex.getMessage)
        println("----- body ----- ")
        println(ex.body)
      case Failure(ex) => println("An error has occured: " + ex.getMessage)
    }

    Await.result(f, Duration.Inf)
  }
}