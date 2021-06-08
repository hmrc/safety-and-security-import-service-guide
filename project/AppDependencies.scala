import play.sbt.PlayImport._
import sbt._

object AppDependencies {
  val bootStrapPlayVersion = "5.2.0"

  val compile: Seq[ModuleID] = Seq(
    ws,
    "uk.gov.hmrc" %% "bootstrap-frontend-play-27" % bootStrapPlayVersion)

  val test: Seq[ModuleID] = Seq(
    "org.pegdown" % "pegdown" % "1.6.0" % "test",
    "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.3" % "test")
}
