package com.screeps.native

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined


@js.native
object Memory extends GlobalMemory {
  val creeps: js.Dictionary[js.Dynamic] = js.native
  val spawns: js.Dictionary[js.Dynamic] = js.native
  val rooms:  js.Dictionary[js.Dynamic] = js.native
  val flags:  js.Dictionary[js.Dynamic] = js.native
}

@ScalaJSDefined
trait GlobalMemory extends Memory

@ScalaJSDefined
trait Memory extends js.Object
