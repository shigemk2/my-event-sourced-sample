name := "my-event-sourced-sample"

organization := "com.transactionalactor"

version := "1.0"

scalaVersion := "2.11.7"

// Change this to another test framework if you prefer

lazy val akkaVersion = "2.3.4"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  // akka-persistence was experimental in older versions
  "com.typesafe.akka" %% "akka-persistence-experimental" % akkaVersion,
  // scala.reflect.runtime is used in examples so we need to add following dependency
  "org.scala-lang" % "scala-reflect" % scalaVersion.value,
  "com.rabbitmq" % "amqp-client" % "3.5.6"
)
