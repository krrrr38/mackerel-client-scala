mackerel-client-scala-example
============================

This is the example for mackerel-client-scala

HostAPI
-------

- HostList
  - `sbt "mackerel-client-scala-example/runMain com.github.krrrr38.mackerel4s.example.HostList apikey"`
- CreateHost
  - `sbt "mackerel-client-scala-example/runMain com.github.krrrr38.mackerel4s.example.CreateHost apikey hostname"`
- GetHost
  - `sbt "mackerel-client-scala-example/runMain com.github.krrrr38.mackerel4s.example.GetHost apikey hostid"`
- UpdateHost
  - `sbt "mackerel-client-scala-example/runMain com.github.krrrr38.mackerel4s.example.UpdateHost apikey hostid newname"`
- UpdateHostStatus
  - `sbt "mackerel-client-scala-example/runMain com.github.krrrr38.mackerel4s.example.UpdateHostStatus apikey hostid newstatus"`
- RetireHost
  - `sbt "mackerel-client-scala-example/runMain com.github.krrrr38.mackerel4s.example.RetireHost apikey hostid"`

TsdbAPI
-------

- PostTsdb
  - `sbt "mackerel-client-scala-example/runMain com.github.krrrr38.mackerel4s.example.PostTsdb apikey hostid metricname"`
- LatestTsdb
  - `sbt "mackerel-client-scala-example/runMain com.github.krrrr38.mackerel4s.example.LatestTsdb apikey hostid"`

ServiceMetricAPI
------

- PostServiceMetric
  - `sbt "mackerel-client-scala-example/runMain com.github.krrrr38.mackerel4s.example.PostServiceMetric apikey servicename metricname"`
