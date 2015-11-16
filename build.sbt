name := "HelloStorm"
net.virtualvoid.sbt.graph.Plugin.graphSettings

version := "1.0"

scalaVersion := "2.11.7"

val stormVersion = "0.10.0"
libraryDependencies ++= Seq(
  "org.apache.kafka" % "kafka_2.11" % "0.8.2.2"
    exclude("org.slf4j", "slf4j-log4j12"),

  "org.apache.storm" % "storm-core" % stormVersion % "provided"
    exclude("org.apache.zookeeper", "zookeeper")
    exclude("org.slf4j", "log4j-over-slf4j"),

  "org.apache.storm" % "storm-kafka" % stormVersion
    exclude("org.apache.zookeeper", "zookeeper"),

  "com.101tec" % "zkclient" % "0.7"
    exclude("org.apache.zookeeper", "zookeeper")
    exclude("org.slf4j", "slf4j-log4j12"),


  "org.apache.curator" % "curator-test" % "2.4.0"
    exclude("org.jboss.netty", "netty")
    exclude("org.slf4j", "slf4j-log4j12"),

  "commons-io" % "commons-io" % "2.4"
    exclude("org.slf4j", "slf4j-log4j12"),

  "org.apache.commons" % "commons-pool2" % "2.3"
    exclude("org.slf4j", "slf4j-log4j12"),

  // Logback with slf4j facade
  "ch.qos.logback" % "logback-classic" % "1.1.2",

  //Mongo Deps
  "org.mongodb" %% "casbah" % "2.8.1",
  "org.mongodb" %% "casbah-commons" % "2.8.1"

)
