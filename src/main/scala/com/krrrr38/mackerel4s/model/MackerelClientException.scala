package com.krrrr38.mackerel4s
package model

import com.ning.http.client.FluentCaseInsensitiveStringsMap

/**
 * When send invalid request, Mackerel send invalid response.
 * This class wrap Mackerel invalid response.
 * @param statusCode
 * @param contentType
 * @param headers
 * @param body
 */
class MackerelResponseError(
  val statusCode: Int,
  val contentType: String,
  val headers: FluentCaseInsensitiveStringsMap,
  val body: String) extends Exception()

/**
 * This class show mackerel-client-scala error.
 * such as invalid json serialization format and so on.
 * @param body
 * @param cause
 */
class MackerelClientException(val message: String, val body: String, cause: Throwable) extends Exception(message, cause) {
  def this(message: String) {
    this(message, "", null)
  }
}
