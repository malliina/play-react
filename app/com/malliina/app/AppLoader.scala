package com.malliina.app

import com.malliina.play.app.DefaultApp
import controllers.{AppTags, Assets, Home, WebJarAssets}
import play.api.ApplicationLoader.Context
import play.api.BuiltInComponentsFromContext
import play.api.routing.Router
import router.Routes

class AppLoader extends DefaultApp(new AppComponents(_))

class AppComponents(context: Context) extends BuiltInComponentsFromContext(context) {
  lazy val assets = new Assets(httpErrorHandler)
  val webJars = new WebJarAssets(httpErrorHandler, configuration, environment)
  val tags = new AppTags(webJars)
  val home = new Home(tags)

  override val router: Router = new Routes(httpErrorHandler, home, assets, webJars)
}
