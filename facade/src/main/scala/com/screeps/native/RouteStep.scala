package com.screeps.native

import scala.scalajs.js

@js.native
trait RouteStep extends js.Object {
  val exit: Int = js.native
  val room: String = js.native
}
