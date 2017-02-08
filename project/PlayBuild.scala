import com.malliina.sbtplay.PlayProject
import com.typesafe.sbt.web.Import.pipelineStages
import com.typesafe.sbt.web.SbtWeb
import com.typesafe.sbt.web.SbtWeb.autoImport.Assets
import org.scalajs.sbtplugin.ScalaJSPlugin
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import play.sbt.PlayImport.PlayKeys
import sbt.Keys._
import sbt._
import webscalajs.ScalaJSWeb
import webscalajs.WebScalaJS.autoImport.{scalaJSPipeline, scalaJSProjects}

object PlayBuild {
  lazy val p = PlayProject.default("play-react")
    .enablePlugins(SbtWeb)
    .settings(playSettings: _*)

  lazy val frontend = project
    .enablePlugins(ScalaJSPlugin, ScalaJSWeb)
    .settings(frontendSettings: _*)

  val utilPlayDep = "com.malliina" %% "util-play" % "3.5.2"

  lazy val playSettings = baseSettings ++ Seq(
    libraryDependencies ++= Seq(
      "org.webjars" %% "webjars-play" % "2.5.0",
      "org.webjars" % "react" % "15.3.2",
      "org.webjars.bower" % "babel-standalone" % "6.14.0",
      utilPlayDep,
      utilPlayDep % Test classifier "tests"
    ),
    PlayKeys.playRunHooks += WebpackHook(baseDirectory.value, streams.value.log),
    scalaJSProjects := Seq(frontend),
    pipelineStages in Assets := Seq(scalaJSPipeline)
  )

  lazy val frontendSettings = baseSettings ++ Seq(
    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % "0.9.1",
      "com.lihaoyi" %%% "scalatags" % "0.6.2"
    ),
    persistLauncher := true
  )

  def baseSettings = Seq(
    organization := "com.malliina",
    version := "0.0.1",
    scalaVersion := "2.11.8"
  )
}
