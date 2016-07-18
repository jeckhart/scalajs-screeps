package com.screeps

import scala.scalajs.js

@js.native
trait PathStep {
  val x: Int = js.native
  val y: Int = js.native
  val dx: Int = js.native
  val dy: Int = js.native
  val direction: Direction.Value = js.native
}
