package controllers

import com.malliina.play.tags.{PlayTags, TagPage, Tags}
import play.api.http.MimeTypes
import play.api.mvc.Call

import scalatags.Text.all._

class AppTags(webJars: WebJarAssets) extends Tags with PlayTags {
  def index(msg: String) = page()(h1(msg))

  def sjs = page(reactHead)(
    h1("Scala.js"),
    div(id := "app"),
    div(id := "app2"),
    jsAssetAt("frontend-fastopt.js"),
    jsAssetAt("frontend-launcher.js")
  )

  def babel = page(reactHead)(
    div(id := "app"),
    script(`type` := "text/babel", src := routes.Assets.at("js/react-demo.js"))
  )

  def react = jsApp("js/react.js")

  def preact = jsApp("js/preact.js")

  private def jsApp(jsFile: String) = page()(
    div(id := "app"),
    script(src := routes.Assets.at(jsFile))
  )

  private def reactHead = Seq(
    webJarScript("react", "react.js"),
    webJarScript("react", "react-dom.js"),
    webJarScript("babel-standalone", "babel.js")
    //    jsScript("https://unpkg.com/react@15/dist/react.js"),
    //    jsScript("https://unpkg.com/react-dom@15/dist/react-dom.js"),
    //    jsScript("https://unpkg.com/babel-standalone@6/babel.min.js")
  )

  def webJarScript(webJar: String, path: String) =
    jsScript(routes.WebJarAssets.at(webJars.fullPath(webJar, path)))

  def jsAssetAt(file: String) = jsAsset(routes.Assets.at(file))

  def jsAsset(file: Call) = script(src := file.toString, `type` := MimeTypes.JAVASCRIPT)

  private def page(heads: Modifier*)(bodyContent: Modifier*) =
    TagPage(
      html(
        head(meta(charset := "UTF-8"), heads),
        body(bodyContent)
      )
    )
}
