mackerel-client-scala
==================

[![Build Status](https://travis-ci.org/krrrr38/mackerel-client-scala.svg)](https://travis-ci.org/krrrr38/mackerel-client-scala)
[![codecov.io](http://codecov.io/github/krrrr38/mackerel-client-scala/coverage.svg?branch=master)](http://codecov.io/github/krrrr38/mackerel-client-scala?branch=master)
[![Maven Central 2.11.x](https://maven-badges.herokuapp.com/maven-central/com.krrrr38/mackerel-client-scala_2.11/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.krrrr38/mackerel-client-scala_2.11)
[![Maven Central 2.10.x](https://maven-badges.herokuapp.com/maven-central/com.krrrr38/mackerel-client-scala_2.10/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.krrrr38/mackerel-client-scala_2.10)
[![License: MIT](http://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)

mackerel-client-scala - Mackerel Scala API Client

Description
-------------

mackerel-client-scala is Scala Client for [Mackerel API](http://help-ja.mackerel.io/entry/spec/api/v0).

Usage
-------------

```scala
libraryDependencies += "com.krrrr38" %% "mackerel-client-scala" % "0.3.0"
```

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
