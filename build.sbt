lazy val root = (project in file("."))
  .enablePlugins(PlayJava, PlayEbean)
  .settings(
    name := "play-test01",
    organization := "com.example",
    version := "2.0.0-SNAPSHOT",
    scalaVersion := "2.13.18",
    libraryDependencies ++= Seq(
      guice,
      jdbc,
      filters,
      "com.h2database" % "h2" % "2.4.240"
    ),
    (Test / testOptions) += Tests.Argument(TestFrameworks.JUnit, "-a", "-v"),
    javacOptions ++= Seq("-Xlint:unchecked", "-Xlint:deprecation", "-Werror")
  )
