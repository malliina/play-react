package com.malliina.react

import org.scalajs.dom

import scala.language.implicitConversions
import scala.scalajs.js
import scala.scalajs.js.Dynamic.{global, literal}
import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport

@js.native
object ReactDOM extends js.Object {
  def render(content: js.Object, node: dom.Node): js.Object = js.native
}

@js.native
object React extends js.Object {
  def createClass(spec: js.Dynamic): js.Dynamic = js.native

//  def createClass(e: ReactDOMElement): ReactClass = js.native

  def createElement(e: js.Dynamic): ReactDOMElement = js.native

  def createElement(tag: String,
                    props: js.Dynamic,
                    children: ReactNode*): ReactDOMElement = js.native
}

@js.native
trait ReactDOMElement extends ReactElement {
  def `type`: String = js.native

  def props: js.Object = js.native
}

@js.native
trait ReactElement extends ReactNode

@js.native
trait ReactNode extends js.Object

object ReactNode {
  implicit def fromString(s: String): ReactNode =
    s.asInstanceOf[ReactNode]
}

@js.native
trait ReactClass extends js.Object

object Frontend extends JSApp {
  val DOM = global.React.DOM

  val MyComp = React.createClass(literal(
    render = {
      ths: js.Dynamic => {
        React.createElement("div", literal(className = "demo!"), "heyey")
      }
    }: js.ThisFunction
  ))

  @JSExport
  override def main() = {
    println("JavaScript rocks")
    val node = dom.document.getElementById("app")
    ReactDOM.render(React.createElement(MyComp), node)
  }
}
