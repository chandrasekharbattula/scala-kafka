ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.0.2"

lazy val root = (project in file("."))
  .settings(
    name := "scala-kafka"
  )

libraryDependencies ++= Seq(
  "org.apache.kafka" % "kafka-clients" % "2.8.0",
  "ch.qos.logback" % "logback-classic" % "1.2.11",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.8.8",
  "com.google.code.gson" % "gson" % "2.8.9",
  "org.scalatest" %% "scalatest" % "3.2.10" % Test,
"org.scalatest" %% "scalatest" % "3.2.12" % Test,
"com.h2database" % "h2" % "1.4.197" % Test,
"junit" % "junit" % "4.13.2" % Test
)