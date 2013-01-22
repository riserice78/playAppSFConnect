import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "playAppSFConnect"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
    	"postgresql" % "postgresql" % "9.1-901-1.jdbc4"
    )

    // Only compile the bootstrap bootstrap.less file and other *.less file in the stylesheets directory
    def customLessEntryPoints(base: File): PathFinder = (
		(base / "app" / "assets" / "stylesheets" / "bootstrap" * "bootstrap.less") +++
		(base / "app" / "assets" / "stylesheets" * "*.less")
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = JAVA).settings(
    		lessEntryPoints <<= baseDirectory(customLessEntryPoints)	 	
    )

}
