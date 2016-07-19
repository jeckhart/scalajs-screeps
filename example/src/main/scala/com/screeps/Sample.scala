package com.screeps

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport
import scala.scalajs.js.Dynamic.{global => g}

@JSExport
object Sample {
  @JSExport
  def loop() = {
    g.c.log("test")
    g.c.log(Game.time)

  }
}