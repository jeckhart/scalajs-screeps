package com.screeps

import scala.scalajs.js

@js.native
trait RouteStep {
  val exit: Direction.Value = js.native
  val room: String = js.native
}
