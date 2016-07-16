import sbt.Keys._

def BaseProject(name: String, path: String): Project =
  Project(name, file(path))
    .settings(
      organization := "io.kalulu",
      version := "0.0.2",
      scalaVersion := "2.11.8",
      scalacOptions ++= Seq("-deprecation", "-feature", "-Xfatal-warnings"),
      homepage := Some(url("https://github.com/jeckhart/scalajs-screeps")),
      licenses +=("BSD 3-Clause", url("http://opensource.org/licenses/BSD-3-Clause")),
      scmInfo := Some(ScmInfo(
        url("https://github.com/jeckhart/scalajs-screeps"),
        "scm:git:git@github.com:jeckhart/scalajs-screeps.git",
        Some("scm:git:git@github.com:jeckhart/scalajs-screeps.git"))),
      publishMavenStyle := true,
      pomExtra :=
        <developers>
          <developer>
            <id>jeckhart</id>
            <name>John Eckhart</name>
            <url>https://github.com/jeckhart/</url>
          </developer>
        </developers>,
      pomIncludeRepository := { _ => false }
    )
    .enablePlugins(ScalaJSPlugin)

lazy val facade = BaseProject("facade", "facade").settings( name := "scalajs-screeps")

