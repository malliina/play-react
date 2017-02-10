package controllers

import play.api.http.Writeable
import play.api.mvc._

class Home(tags: AppTags) extends Controller {
  def index = okAction(tags.index("Hoi!!!"))

  def babel = okAction(tags.babel)

  def sjs = okAction(tags.sjs)

  def react = okAction(tags.react)

  def preact = okAction(tags.preact)

  def okAction[C: Writeable](content: C) = Action(Ok(content))
}
