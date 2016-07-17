package com.screeps

import scala.scalajs.js

@js.native
trait RoutePath {
  val exit: Direction.Value = js.native
  val room: String = js.native
}
