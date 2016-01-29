import sbt._
import Keys._

object Dependencies {
  val json4sJackson = "org.json4s" %% "json4s-jackson" % "3.2.11"
  val dispatch = "net.databinder.dispatch" %% "dispatch-core" % "0.11.2"
  val scalatest = "org.scalatest" %% "scalatest" % "2.2.1" % Test
  val scalamock = "org.scalamock" %% "scalamock-scalatest-support" % "3.2" % Test
  val jettyServer = "org.eclipse.jetty" % "jetty-server" % "9.2.5.v20141112" % Test
}

object BuildSettings {

  import scala.sys.process._
  import scala.Console.{CYAN, RESET}
  import xerial.sbt.Sonatype.SonatypeKeys.sonatypeProfileName

  val buildSettings =
    com.typesafe.sbt.SbtScalariform.scalariformSettings ++ Seq(
      organization := "com.krrrr38",
      scalaVersion := "2.11.7",
      crossScalaVersions := scalaVersion.value :: "2.10.5" :: Nil,
      version := "0.3.1-SNAPSHOT",
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

  lazy val publishSettings = Seq(
    sonatypeProfileName := "com.krrrr38",
    pomExtra := {
      <url>http://github.com/krrrr38/mackerel-client-scala</url>
          <scm>
            <url>git@github.com:krrrr38/mackerel-client-scala.git</url>
            <connection>scm:git:git@github.com:krrrr38/mackerel-client-scala.git</connection>
          </scm>
          <licenses>
            <license>
              <name>MIT License</name>
              <url>http://www.opensource.org/licenses/mit-license.php</url>
              <distribution>repo</distribution>
            </license>
          </licenses>
          <developers>
            <developer>
              <id>krrrr38</id>
              <name>Ken Kaizu</name>
              <url>http://www.krrrr38.com</url>
            </developer>
          </developers>
    },
    publishArtifact in Test := false,
    publishMavenStyle := true,
    pomIncludeRepository := { _ => false },
    publishTo <<= version { (v: String) =>
      val nexus = "https://oss.sonatype.org/"
      if (v.trim.endsWith("SNAPSHOT"))
        Some("snapshots" at nexus + "content/repositories/snapshots")
      else
        Some("releases" at nexus + "service/local/staging/deploy/maven2")
    }
  )
}

object MackerelClientBuild extends Build {

  import BuildSettings._
  import Dependencies._

  lazy val root = Project(
    "mackerel-client-scala",
    file("."),
    settings = buildSettings ++ publishSettings ++ Seq(
      name := "mackerel-client-scala",
      description := "Mackerel Scala API Client",
      libraryDependencies ++= Seq(
        json4sJackson, dispatch,
        scalatest, scalamock, jettyServer
      ),
      parallelExecution in Test := false
    )
  )
}
