package com.screeps

import scala.scalajs.js

@js.native
trait RouteStep extends js.Object {
  val exit: Direction.Value = js.native
  val room: String = js.native
}
