import sbt._
import Keys._

object Dependencies {
  val json4sJackson = "org.json4s" %% "json4s-jackson" % "3.2.11"
  val dispatch = "net.databinder.dispatch" %% "dispatch-core" % "0.11.2"
  val scalatest = "org.scalatest" %% "scalatest" % "2.2.1" % "test"
  val scalamock = "org.scalamock" %% "scalamock-scalatest-support" % "3.2" % "test"
  val jettyServer = "org.eclipse.jetty" % "jetty-server" % "9.2.5.v20141112" % "test"
}

object BuildSettings {
  import scala.sys.process._
  import scala.Console.{ CYAN, RESET }

  val buildSettings =
    com.typesafe.sbt.SbtScalariform.scalariformSettings ++ Seq(
      organization := "com.github.krrrr38",
      scalaVersion := "2.11.4",
      crossScalaVersions := scalaVersion.value :: "2.10.4" :: Nil,
      scalacOptions ++= (
        "-deprecation" ::
          "-feature" ::
          "-unchecked" ::
          "-Xlint" ::
          Nil
        ),
      scalacOptions ++= {
        if (scalaVersion.value.startsWith("2.11"))
          Seq("-Ywarn-unused", "-Ywarn-unused-import")
        else
          Nil
      },
      shellPrompt := { state =>
        val branch = Process("git rev-parse --abbrev-ref HEAD").lines.headOption.getOrElse("N/A")
        s"[$CYAN${name.value}$RESET#$CYAN$branch$RESET] "
      }
    )
}

object MackerelClientBuild extends Build {
  import BuildSettings._
  import Dependencies._

  lazy val root = Project(
    "mackerel-client-scala",
    file("."),
    settings = buildSettings ++ Seq(
      name := "mackerel-client-scala",
      version := "0.1.0",
      description := "Mackerel Scala API Client",
      libraryDependencies ++= Seq(
        json4sJackson, dispatch,
        scalatest, scalamock, jettyServer
      )
    )
  )
}
