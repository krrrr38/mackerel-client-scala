package com.krrrr38.mackerel4s
package builder

import dispatch.Req
import org.json4s.NoTypeHints
import org.json4s.jackson.Serialization

import com.krrrr38.mackerel4s.model.{ GraphDef, HostMetric, SuccessResponse }
import com.krrrr38.mackerel4s.model.Types.Path

object PostGraphDefsBuilder extends APIBuilder[Unit] {
  override val FullPath = (_: Unit) => "/graph-defs/create"
  override val MethodVerb = MethodVerbPost

  def apply(client: Path => Req, graphDefs: Seq[GraphDef]): PostGraphDefsBuilder =
    PostGraphDefsBuilder(baseRequest(client, ()), graphDefs)
}

private[builder] case class PostGraphDefsBuilder(private val req: Req, graphDefs: Seq[GraphDef]) extends RequestBuilder[SuccessResponse] {
  /**
   * build request with parameters before run http request
   * @return
   */
  override protected def buildRequest: Req =
    req.setBody(Serialization.write(graphDefs))

  def addGraphDef(graphDef: GraphDef) = this.copy(graphDefs = graphDef +: this.graphDefs)
  def addGraphDefs(graphDefs: Seq[GraphDef]) = this.copy(graphDefs = graphDefs ++ this.graphDefs)
}
