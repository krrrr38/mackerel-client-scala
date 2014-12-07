package com.krrrr38

import org.scalatest.concurrent.PatienceConfiguration
import org.scalatest.time.{ Span, Seconds }

package object mackerel4s {
  val patience = PatienceConfiguration.Timeout(Span(5, Seconds))
}
