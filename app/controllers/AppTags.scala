package controllers

import com.malliina.play.tags.{PlayTags, TagPage, Tags}

import scalatags.Text.all._

class AppTags(webJars: WebJarAssets) extends Tags with PlayTags {
  def index(msg: String) = page()(h1(msg))

  def react = page(reactHead)(
    div(id := "app"),
    script(`type` := "text/babel", src := routes.Assets.at("js/react-demo.js"))
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

  private def page(heads: Modifier*)(content: Modifier*) =
    TagPage(
      html(
        head(meta(charset := "UTF-8"), heads),
        body(content)
      )
    )
}
