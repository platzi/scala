name := """platzi-video"""
organization := "com.platzi"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala, DockerPlugin)

scalaVersion := "2.13.1"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.3" % Test
libraryDependencies += "com.typesafe.play" %% "play-slick" % "4.0.2"
libraryDependencies += "org.xerial" % "sqlite-jdbc" % "3.28.0"

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.example.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.example.binders._"


// Configuración para Docker
// Más info en: https://sbt-native-packager.readthedocs.io/en/stable/formats/docker.html
import com.typesafe.sbt.packager.docker._

dockerBaseImage := "openjdk:8-jre-alpine"
dockerExposedPorts ++= Seq(9000)

daemonUserUid in Docker := None
daemonUser in Docker := "daemon"

// La imagen de alpine no viene con bash por defecto, por eso se setea el usuario a root y se instala.
dockerCommands += Cmd("USER", "root")
dockerCommands += ExecCmd("RUN", "/bin/sh", "-c", "apk add --no-cache bash")