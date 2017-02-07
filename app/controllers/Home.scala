package controllers

import play.api.http.Writeable
import play.api.mvc._

class Home(tags: AppTags) extends Controller {
  def index = okAction(tags.index("Hoi!!!"))

  def react = okAction(tags.react)

  def web = okAction(tags.web)

  def okAction[C: Writeable](content: C) = Action(Ok(content))
}
