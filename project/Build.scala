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
      organization := "com.krrrr38",
      scalaVersion := "2.11.4",
      crossScalaVersions := scalaVersion.value :: "2.10.4" :: Nil,
      version := "0.2.1",
      isSnapshot := false,
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
      },
      pomExtra :=
        <url>http://github.com/krrrr38/mackerel-client-scala</url>
          <scm>
            <url>git@github.com:krrrr38/mackerel-client-scala.git</url>
            <connection>scm:git:git@github.com:krrrr38/mackerel-client-scala.git</connection>
          </scm>
          <developers>
            <developer>
              <id>krrrr38</id>
              <name>Ken Kaizu</name>
              <url>http://www.krrrr38.com</url>
            </developer>
          </developers>,
      publishArtifact in Test := false,
      publishMavenStyle := true,
      publishTo := {
        val ghpageMavenDir: Option[String] =
          if (Process("which ghq").! == 0) {
            (Process("ghq list --full-path") #| Process("grep krrrr38/maven")).lines.headOption
          } else None
        ghpageMavenDir.map { dirPath =>
          Resolver.file(
            organization.value,
            file(dirPath)
          )(Patterns(true, Resolver.mavenStyleBasePattern))
        }
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
      description := "Mackerel Scala API Client",
      libraryDependencies ++= Seq(
        json4sJackson, dispatch,
        scalatest, scalamock, jettyServer
      ),
      parallelExecution in Test := false
    )
  )

  lazy val example = Project(
    "mackerel-client-scala-example",
    file("example"),
    settings = buildSettings ++ Seq(
      name := "mackerel-client-scala-example"
    )
  ).dependsOn(root).aggregate(root)
}
