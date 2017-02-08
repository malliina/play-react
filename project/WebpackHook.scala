import play.sbt.PlayRunHook
import sbt._

object WebpackHook {
  def apply(baseDir: File, log: Logger) = new WebpackHook(baseDir, log)
}

class WebpackHook(baseDir: File, log: Logger) extends PlayRunHook {
  val isWindows = sys.props.get("os.name").exists(_.startsWith("Windows"))
  val webpackFile = if (isWindows) "webpack.cmd" else "webpack"
  val webpackPath = baseDir / "node_modules" / ".bin" / webpackFile
  val prefix = ""
  // if (isWindows) "cmd /c " else ""
  var installProcess: Option[Process] = None
  var watchProcess: Option[Process] = None

  val InstallCommand = "npm install"

  override def beforeStarted() = {
    if (!webpackPath.exists()) {
      log.info(s"File not found: $webpackPath. Running 'npm install'...")
      val process = run(InstallCommand)
      installProcess = Option(process)
      // Blocks so that we only start watching after the install
      val exitValue = process.exitValue()
      if (exitValue != 0) {
        sys.error(s"Non-zero exit value from '$InstallCommand': $exitValue.")
      }
      installProcess = None
    }

//    watchProcess = Option(run("npm run build:watch"))
    watchProcess = Option(run(s"$webpackPath --watch"))
  }

  override def afterStopped() = {
    exit()
  }

  override def onError() = {
    log.error(s"Play hook failure")
    exit()
  }

  def exit() = {
    installProcess foreach { p =>
      log info "Stopping npm install..."
      p.destroy()
    }
    installProcess = None
    watchProcess foreach { p =>
      log info "Stopping webpack watch..."
      p.destroy()
    }
    watchProcess = None
  }

  def run(command: String) = Process(s"$prefix$command", baseDir).run(log)
}
