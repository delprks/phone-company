name := "phone-company"

version := "1.0"

scalaVersion := "2.12.6"

val assemblySettings = Seq(
  test in assembly := {},
  assemblyJarName in assembly := s"${name.value}-${version.value}.jar"
)

val applicationDependencies = Seq(
  "org.scalatest" %% "scalatest" % "3.0.1"
)

lazy val root = (project in file("."))
  .settings(
    organization := "com.phone",
    libraryDependencies ++= applicationDependencies
  )
  .settings(assemblySettings)
