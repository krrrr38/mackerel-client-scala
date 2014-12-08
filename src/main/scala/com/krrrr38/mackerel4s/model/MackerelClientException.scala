package com.krrrr38.mackerel4s
package model

import com.ning.http.client.FluentCaseInsensitiveStringsMap

/**
 * When send invalid request, Mackerel send invalid response.
 * This class wrap Mackerel invalid response.
 * @param code
 * @param contentType
 * @param headers
 * @param body
 */
class MackerelResponseError(
  val code: Int,
  val contentType: String,
  val headers: FluentCaseInsensitiveStringsMap,
  val body: String) extends Exception()

/**
 * This class show mackerel-client-scala error.
 * such as invalid json serialization format and so on.
 * @param body
 * @param cause
 */
class MackerelClientException(val body: String, cause: Throwable) extends Exception(cause)
