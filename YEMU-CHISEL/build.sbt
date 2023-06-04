
scalaVersion := "2.12.13"

resolvers ++= Seq (
  Resolver.sonatypeRepo ("snapshots"),
  Resolver.sonatypeRepo ("releases")
)

scalacOptions ++= Seq("-Xsource:2.11")

libraryDependencies += "edu.berkeley.cs" %% "chisel3" % "3.4.4"


