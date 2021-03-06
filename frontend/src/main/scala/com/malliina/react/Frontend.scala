package com.malliina.react

import org.scalajs.dom

import scala.language.implicitConversions
import scala.scalajs.js
import scala.scalajs.js.Dynamic.literal
import scala.scalajs.js.annotation.{JSExport, ScalaJSDefined}
import scala.scalajs.js.{Date, JSApp}

/** General notes: It's unclear how to handle Component state if we can't
  * extend React.Component.
  *
  * Therefore this research is on hold currently.
  */

@js.native
object ReactDOM extends js.Object {
  def render(content: ReactDOMElement, node: dom.Node): js.Object = js.native
}

@js.native
object React extends js.Object {

  @ScalaJSDefined
  trait Component extends js.Object

  def createClass(spec: js.Dynamic): js.Dynamic = js.native

  def createClass(spec: Renderable): ReactClass = js.native

  def createElement(e: js.Dynamic): ReactDOMElement = js.native

  def createElement(e: ReactClass): ReactDOMElement = js.native

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

@ScalaJSDefined
class MyCompES6 extends React.Component {
  def render(): ReactDOMElement = {
    React.createElement("div", literal(className = "demo!"), "hello, world")
  }
}

@ScalaJSDefined
abstract class Renderable extends js.Object {
  def render(): ReactDOMElement
}

object Frontend extends JSApp {
  val compCtor = js.constructorOf[MyCompES6]

  val MyComp = React.createClass(literal(
    render = {
      ths: js.Dynamic => {
        React.createElement("div", literal(className = "demo!"), "hello, world")
      }
    }
  ))

  val Clock = createDom(React.createElement("p", null, new Date().toLocaleTimeString()))

  val MyComp2 = createDom(
    React.createElement(
      "div",
      literal(className = "demo!"),
      React.createElement(Clock)
    )
  )

  def createDom(e: => ReactDOMElement) = React.createClass(new Renderable {
    override def render(): ReactDOMElement = e
  })

  @JSExport
  override def main() = {
    println("JavaScript rocks")
    val node = dom.document.getElementById("app")

    def render = {
      ReactDOM.render(React.createElement(MyComp2), node)
    }

    dom.window.setInterval(() => render, 100)
  }
}
