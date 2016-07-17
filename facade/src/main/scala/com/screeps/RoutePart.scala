package com.screeps

import scala.scalajs.js

@js.native
trait RoutePart {
  val exit: Direction.Value = js.native
  val room: String = js.native
}
