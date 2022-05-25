lazy val gitRepoHost  = "codeberg.org"
lazy val gitRepoUser  = "sciss"

lazy val commonSettings = Seq(
  name               := "StripAndEmbellish",
  version            := "0.8.0-SNAPSHOT",
  organization       := "de.sciss",
  scalaVersion       := "2.13.8",
  licenses           := Seq("GPL v3+" -> url("http://www.gnu.org/licenses/gpl-3.0.txt")),
  homepage           := Some(url(s"https://$gitRepoHost/$gitRepoUser/${name.value}")),
//  resolvers          += "Oracle Repository" at "http://download.oracle.com/maven",  // required for sleepycat
  scalacOptions     ++= Seq("-deprecation", "-unchecked", "-feature", "-encoding", "utf8", "-Xlint:-stars-align,_"),
  scalacOptions      += "-Yrangepos",  // this is needed to extract source code
  updateOptions      := updateOptions.value.withLatestSnapshots(false)
)

lazy val deps = new {
  val fscape          = "3.14.0"
  val lucre           = "4.6.0"
  val negatum         = "1.13.0"
  val soundProcesses  = "4.14.2"
  val submin          = "0.3.5"
  val ugen            = "1.21.3"
  val wolkenpumpe     = "3.11.0"
}

lazy val mainCl = "de.sciss.embellish.Main"

lazy val assemblySettings = Seq(
  assembly / assemblyJarName := s"${name.value}.jar",
  assembly / target          := baseDirectory.value,
  assembly / mainClass       := Some(mainCl),
  assembly / assemblyMergeStrategy := {
    case "logback.xml" => MergeStrategy.last
    case PathList("org", "xmlpull", _ @ _*)              => MergeStrategy.first
    case PathList("org", "w3c", "dom", "events", _ @ _*) => MergeStrategy.first // bloody Apache Batik
    case x =>
      val old = (assembly / assemblyMergeStrategy).value
      old(x)
  }
)

lazy val root = project.in(file("."))
  .enablePlugins(BuildInfoPlugin)
  .settings(commonSettings)
  .settings(assemblySettings)
  .settings(
    Compile / run / mainClass := Some(mainCl),
    run / fork  := true,
    libraryDependencies ++= Seq(
      "de.sciss" %% "fscape-lucre"                % deps.fscape,
      "de.sciss" %% "fscape-macros"               % deps.fscape,
      "de.sciss" %% "lucre-bdb"                   % deps.lucre,
      "de.sciss" %% "negatum-core"                % deps.negatum,
      "de.sciss" %% "scalacolliderugens-plugins"  % deps.ugen,
      "de.sciss" %% "soundprocesses-core"         % deps.soundProcesses,
      "de.sciss" %  "submin"                      % deps.submin,
      "de.sciss" %% "wolkenpumpe"                 % deps.wolkenpumpe,
    ),
    buildInfoPackage := "de.sciss.anemone",
    buildInfoKeys := Seq(name, organization, version, scalaVersion, description,
      BuildInfoKey.map(homepage) { case (k, opt)           => k -> opt.get },
      BuildInfoKey.map(licenses) { case (_, Seq((lic, _))) => "license" -> lic }
    ),
    buildInfoOptions += BuildInfoOption.BuildTime,
  )
