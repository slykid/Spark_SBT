ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "spark_sbt"
  )

libraryDependencies += "org.apache.spark" %% "spark-sql" % "3.2.1"
libraryDependencies += "org.apache.spark" %% "spark-core" % "3.2.1"
libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.6.13"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.19" % Test