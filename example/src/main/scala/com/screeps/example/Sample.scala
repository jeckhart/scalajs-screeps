package com.screeps.example

import com.screeps.native._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport
import scala.scalajs.js.Dynamic.{global => g}

@JSExport
object Sample {
  @JSExport
  def loop() = {
    Game.creeps.values.map( creep => {
      if (creep.carry("energy") < creep.carryCapacity) {
        val sources = creep.room.find(FindType.Sources.id).asInstanceOf[js.Array[Source]]
        if (creep.harvest(sources.head) == Errors.NotInRange.id) {
          creep.moveTo(sources(0).pos)
        }
      } else {
        if (creep.transfer(Game.spawns("Spawn1"), ResourceType.Energy.toString) == Errors.NotInRange.id) {
          creep.moveTo(Game.spawns("Spawn1").pos)
        }
      }
    })
  }
}

