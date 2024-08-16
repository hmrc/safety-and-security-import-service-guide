
val appName = "safety-and-security-import-service-guide"

lazy val microservice = Project(appName, file("."))
  .enablePlugins(play.sbt.PlayScala, SbtDistributablesPlugin)
  .settings(
    libraryDependencies ++= AppDependencies.compile ++ AppDependencies.test,
    majorVersion := 0,
    scalaVersion := "2.13.14",
    PlayKeys.playDefaultPort := 4567,
    scalacOptions ++= Seq("-feature"),
    scalacOptions += "-Wconf:cat=unused-imports&src=routes/.*:s"
  )
  .settings(
    resolvers += Resolver.jcenterRepo
  )
