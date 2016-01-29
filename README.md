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

Status
------------

|category|api|action|status|
|---|---|---|:---:|
|Services|List of Services|GET /api/v0/services|:white_check_mark:|
|Roles|List of Roles|GET /api/v0/services/<serviceName>/roles|:white_check_mark:|
|Hosts|Registering host information|POST /api/v0/hosts|:white_check_mark:|
||Getting host information|GET /api/v0/hosts/<hostId>|:white_check_mark:|
||Updating host information|PUT /api/v0/hosts/<hostId>|:white_check_mark:|
||Updating host status|POST /api/v0/hosts/<hostId>/status|:white_check_mark:|
||Retiring a host|POST /api/v0/hosts/<hostId>/retire|:white_check_mark:|
||List of hosts|GET /api/v0/hosts|:white_check_mark:|
|Metrics|Posting metrics|POST /api/v0/tsdb|:white_check_mark:|
||Getting host metrics|GET /api/v0/hosts/<hostId>/metrics|:white_check_mark:|
||Getting latest metrics|GET /api/v0/tsdb/latest|:white_check_mark:|
||Posting graph definitions|POST /api/v0/graph-defs/create|:white_check_mark:|
||Posting service metrics|POST /api/v0/services/<serviceName>/tsdb|:white_check_mark:|
||Getting service metrics|GET /api/v0/services/<serviceName>/metrics|:white_check_mark:|
||Posting monitoring check results|POST /api/v0/monitoring/checks/report|:white_check_mark:|
|Monitors|Register monitor configurations|POST /api/v0/monitors|:white_check_mark:|
||Get monitor configurations|GET /api/v0/monitors|:white_check_mark:|
||Update monitor configurations|PUT /api/v0/monitors/<monitorId>|:white_check_mark:|
||Delete monitor configurations|DELETE /api/v0/monitors/<monitorId>|:white_check_mark:|
|Alerts|Getting Alerts|GET /api/v0/alerts|:white_check_mark:|
||Closing Alerts|POST /api/v0/alerts/<alertId>/close|:white_check_mark:|
|Dashboards|Creating Dashboards|POST /api/v0/dashboards|:no_entry_sign:|
||Getting Dashboards|GET /api/v0/dashboards/<dashboardId>|:no_entry_sign:|
||Updating Dashboards|PUT /api/v0/dashboards/<dashboardId>|:no_entry_sign:|
||Deleting Dashboards|DELETE /api/v0/dashboards/<dashboardId>|:no_entry_sign:|
||List of Dashboards|GET /api/v0/dashboards|:no_entry_sign:|
|Users|List of users|GET /api/v0/users|:no_entry_sign:|
||Delete users|DELETE /api/v0/users/<userId>|:no_entry_sign:|

Contribution
---------------
1. Fork (https://github.com/krrrr38/mackerel-client-scala)
2. Create a feature branch
3. Commit your changes
4. Rebase your local changes against the master branch
5. Run test with `sbt test`(`sbt clean coverage test`) and confirm that it passes
7. Create new Pull Request
