name := "scala-mars-rover"

organization := "net.d53dev"

version := "0.0.1"

scalaVersion := "2.12.1"

libraryDependencies ++= Seq(
  "org.specs2" %% "specs2-core" % "3.8.8"  % "test",
  "org.specs2" %% "specs2-scalacheck"  % "3.8.8" % "test",
  "org.specs2" %% "specs2-mock"  % "3.8.8" % "test",
  "org.scalacheck" %% "scalacheck" % "1.13.4"
)

scalacOptions in Test ++= Seq("-Yrangepos")

