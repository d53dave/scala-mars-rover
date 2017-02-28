name := "scala-mars-rover"

organization := "net.d53dev"

version := "0.0.1"

scalaVersion := "2.11.5"

libraryDependencies ++= Seq(
  "org.specs2" 		%% "specs2-core" % "3.8.8" 	% "test",
  "org.scalacheck"  %% "scalacheck"  % "1.13.4" % "test"
)

scalacOptions in Test ++= Seq("-Yrangepos")

initialCommands := "import net.d53dev.scalamarsrover._"

