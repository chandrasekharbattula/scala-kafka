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
  "com.google.code.gson" % "gson" % "2.8.9"
)