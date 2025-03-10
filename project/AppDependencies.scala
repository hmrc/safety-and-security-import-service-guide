import play.sbt.PlayImport._
import sbt._

object AppDependencies {
  val bootStrapPlayVersion = "9.11.0"

  val compile: Seq[ModuleID] = Seq(
    ws,
    "uk.gov.hmrc" %% "bootstrap-frontend-play-30" % bootStrapPlayVersion
  )

  val test: Seq[ModuleID] = Seq(
    "uk.gov.hmrc"            %% "bootstrap-test-play-30" % bootStrapPlayVersion
  ).map(_ % Test)
}
