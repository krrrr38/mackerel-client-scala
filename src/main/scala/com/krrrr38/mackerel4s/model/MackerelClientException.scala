package com.krrrr38.mackerel4s
package model

/**
 * When send invalid request, Mackerel send invalid response.
 * This class wrap Mackerel invalid response.
 * @param body
 */
class MackerelResponseException(val body: String) extends Exception()

/**
 * This class show mackerel-client-scala error.
 * such as invalid json serialization format and so on.
 * @param body
 * @param cause
 */
class MackerelClientException(val body: String, cause: Throwable) extends Exception(cause)
