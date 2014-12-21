mackerel-client-scala
==================

[![Build Status](https://travis-ci.org/krrrr38/mackerel-client-scala.svg)](https://travis-ci.org/krrrr38/mackerel-client-scala)
<!-- [![Coverage Status](https://img.shields.io/coveralls/krrrr38/mackerel-client-scala.svg)](https://coveralls.io/r/krrrr38/mackerel-client-scala) -->

mackerel-client-scala - Mackerel Scala API Client

Description
-------------

mackerel-client-scala is Scala Client for [Mackerel API](http://help-ja.mackerel.io/entry/spec/api/v0).

Usage
-------------

```scala
resolvers += "Maven Repository on Github" at "http://krrrr38.github.io/maven/"

libraryDependencies += "com.krrrr38" %% "mackerel-client-scala" % "0.2.1"
```

And there is an example project. Plsese See [Example README](https://github.com/krrrr38/mackerel-client-scala/tree/master/example)

Synopsis
------------

```scala
import com.krrrr38.mackerel4s.MackerelClient
import com.krrrr38.mackerel4s.model.MackereResponseError
val mackerel = new MackerelClient("api-key", "user-agent")
mackerel.listHosts.setService("service-name").run onComplete {
  case Success(res) => ...
  case Failure(ex: MackereResponseError) => s.statusCode ...
  case Failure(ex) => ...
}
```

Contribution
---------------
1. Fork (https://github.com/krrrr38/mackerel-client-scala)
2. Create a feature branch
3. Commit your changes
4. Rebase your local changes against the master branch
5. Run test with `sbt test`(`sbt clean coverage test`) and confirm that it passes
7. Create new Pull Request
