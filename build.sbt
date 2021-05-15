name := "kafka-throughput-investigation"

version := "0.1"

scalaVersion := "2.13.5"

libraryDependencies += "com.fasterxml.jackson.core" % "jackson-core" % "2.12.3"
libraryDependencies += "com.fasterxml.jackson.core" % "jackson-databind" % "2.12.3"
libraryDependencies += "org.json4s" %% "json4s-jackson" % "3.6.9"
libraryDependencies += "org.apache.kafka" %% "kafka" % "2.8.0"
libraryDependencies += "au.com.bytecode" % "opencsv" % "2.4"