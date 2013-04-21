name := "streamer"

version := "0.0.1"

organization := "com.mosesn"

scalaVersion := "2.10.1"

crossScalaVersions := Seq("2.9.1", "2.9.2", "2.9.3", "2.10.1")

libraryDependencies += "org.scalatest" %% "scalatest" % "1.9.1"

scalacOptions += "-deprecation"

publishMavenStyle := true

publishTo <<= version { (v: String) =>
    val nexus = "https://oss.sonatype.org/"
    if (v.trim.endsWith("SNAPSHOT"))
          Some("snapshots" at nexus + "content/repositories/snapshots")
    else
          Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

pomIncludeRepository := { _ => false }

publishArtifact in Test := false

pomExtra := (
    <url>https://github.com/mosesn/streamer</url>
    <licenses>
      <license>
        <name>MIT License</name>
        <url>http://opensource.org/licenses/mit-license.php</url>
        <distribution>repo</distribution>
      </license>
    </licenses>
    <scm>
      <url>git@github.com:mosesn/streamer.git</url>
      <connection>scm:git:git@github.com:mosesn/streamer.git</connection>
    </scm>
    <developers>
      <developer>
        <id>mosesn</id>
        <name>Moses Nakamura</name>
        <url>http://github.com/mosesn</url>
      </developer>
    </developers>)
