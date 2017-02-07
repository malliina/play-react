import java.net.InetSocketAddress

import play.sbt.PlayRunHook
import sbt.{File, Logger, Process}

object WebpackHook {
  def apply(baseDir: File, log: Logger) = new WebpackHook(baseDir, log)
}

class WebpackHook(baseDir: File, log: Logger) extends PlayRunHook {
  var process: Option[Process] = None
  var watchProcess: Option[Process] = None

  override def beforeStarted() = {
    process = cmd("npm run build")
  }

  override def afterStarted(addr: InetSocketAddress) = {
    watchProcess = cmd("npm run build:watch")
  }

  override def afterStopped() = {
    log.info("Stopping NPM...")
    exit()
  }

  override def onError() = {
    log.error(s"NPM failure")
    exit()
  }

  def exit() = {
    process.foreach(_.destroy())
    process = None
    watchProcess.foreach(_.destroy())
    watchProcess = None
  }

  def cmd(command: String) = {
    val path = sys.env.getOrElse("Path", "No path defined")
    log.info(s"Using $path")
    Option(run(Process(command, baseDir, "PATH" -> path)))
  }

  def run(builder: sbt.ProcessBuilder) = builder.run(log)
}
