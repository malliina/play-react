import java.net.InetSocketAddress

import play.sbt.PlayRunHook
import sbt.{File, Logger, Process}

object WebpackHook {
  def apply(baseDir: File, log: Logger) = new WebpackHook(baseDir, log)
}

class WebpackHook(baseDir: File, log: Logger) extends PlayRunHook {
  val isWindows = sys.props.get("os.name").exists(_.startsWith("Windows"))
  val prefix = if (isWindows) "cmd /c " else ""
  var buildProcess: Option[Process] = None
  var watchProcess: Option[Process] = None

  val BuildCommand = "npm run build"

  override def beforeStarted() = {
    val process = run(BuildCommand)
    buildProcess = Option(process)
    // Blocks so that we only start watching after the initial build is complete
    val exitValue = process.exitValue()
    if(exitValue != 0) {
      sys.error(s"Non-zero exit value from '$BuildCommand': $exitValue.")
    }
  }

  override def afterStarted(addr: InetSocketAddress) = {
    watchProcess = Option(run("npm run build:watch"))
  }

  override def afterStopped() = {
    log.info("Stopping npm...")
    exit()
  }

  override def onError() = {
    log.error(s"npm failure")
    exit()
  }

  def exit() = {
    buildProcess foreach { p =>
      log info "Stopping npm build..."
      p.destroy()
    }
    buildProcess = None
    watchProcess foreach { p =>
      log info "Stopping npm watch..."
      p.destroy()
    }
    watchProcess = None
  }

  def run(command: String) = Process(s"$prefix$command", baseDir).run(log)
}
