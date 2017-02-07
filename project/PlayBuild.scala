import com.malliina.sbtplay.PlayProject
import play.sbt.PlayImport.PlayKeys
import sbt.Keys._
import sbt._

object PlayBuild {
  lazy val p = PlayProject.default("play-react").settings(commonSettings: _*)
  val printEnv = taskKey[Unit]("prints env")
  val utilPlayDep = "com.malliina" %% "util-play" % "3.5.2"

  lazy val commonSettings = Seq(
    organization := "com.malliina",
    version := "0.0.1",
    scalaVersion := "2.11.8",
    libraryDependencies ++= Seq(
      "org.webjars" %% "webjars-play" % "2.5.0",
      "org.webjars" % "react" % "15.3.2",
      "org.webjars.bower" % "babel-standalone" % "6.14.0",
      utilPlayDep,
      utilPlayDep % Test classifier "tests"
    ),
    PlayKeys.playRunHooks += WebpackHook(baseDirectory.value, streams.value.log),
    printEnv := streams.value.log.info(sys.env.getOrElse("Path", "No path defined."))
  )
}
