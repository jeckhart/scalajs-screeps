package com.screeps.example

import com.screeps.native._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport
import scala.scalajs.js.Dynamic.{global => g}

@JSExport
object Sample {
  @JSExport
  def loop() = {
    val num = numOfHarvesters(Game.creeps.values)
    g.c.log("Harvesters: " + num)
    if (num < 2) {
      val newName = Game.spawns("Spawn1").createCreep(js.Array("work", "carry", "move"), "", new js.Object{ val role = "harvester"})
      g.c.log("Spawning new harvester: " + newName)
    }
    cleanMemory()

    Game.creeps.values.map( creep => {
      creep.memory.role.asInstanceOf[String] match {
        case "harvester" => harvester(creep)
        case "upgrader"  => upgrader(creep)
        case "builder"   => builder(creep)
        case unknown     => g.c.log("unknown role: " + unknown)
      }
    })
  }

  def numOfHarvesters(creeps: Iterable[Creep]) =
    creeps.filter(_.memory.role.asInstanceOf[String] == "harvester").size

  def cleanMemory() = {
    Memory.creeps.keys.filterNot(Game.creeps.contains).foreach { name: String =>
      g.c.log("Recycling memories of " + name)
      Memory.creeps.delete(name)
    }
  }

  def harvester(creep: Creep) = {
    if (creep.carry("energy") < creep.carryCapacity) {
      harvest(creep)
    } else {
      val opts = new js.Object {
        def filter(structure:Structure): Boolean = {
          return (structure.structureType == StructureType.Extension.toString ||
            structure.structureType == StructureType.Spawn.toString ||
            structure.structureType == StructureType.Tower.toString ) && (
            structure.asInstanceOf[OwnedStructureWithEnergy].energy < structure.asInstanceOf[OwnedStructureWithEnergy].energyCapacity)
        }
      }.asInstanceOf[js.Object]
      val targets = creep.room.find(FindType.Structures.id, opts).asInstanceOf[js.Array[Structure]]
      targets.headOption.foreach{ target =>
        if(creep.transfer(target, ResourceType.Energy.toString) == Errors.NotInRange.id ) {
          creep.moveTo(target.pos)
        }
      }
    }
  }

  def harvest(creep:Creep) = {
    val sources = creep.room.find(FindType.Sources.id).asInstanceOf[js.Array[Source]]
    if (creep.harvest(sources.head) == Errors.NotInRange.id) {
      creep.moveTo(sources(0).pos)
    }
  }

  def upgrader(creep: Creep) = {
    if (creep.carry("energy") == 0) {
      harvest(creep)
    } else {
      if(creep.upgradeController(creep.room.controller) == Errors.NotInRange.id) {
        creep.moveTo(creep.room.controller.pos)
      }
    }
  }

  def builder(creep: Creep) = {
    if (js.isUndefined(creep.memory.building))
      creep.memory.building = false

    if (creep.memory.building.asInstanceOf[Boolean] && creep.carry("energy") == 0) {
      creep.memory.building = false
      creep.say("harvesting")
    }
    if (!creep.memory.building.asInstanceOf[Boolean] && creep.carry("energy") == creep.carryCapacity) {
      creep.memory.building = true
      creep.say("building")
    }

    if (creep.memory.building.asInstanceOf[Boolean]) {
      creep.room.find(FindType.ConstructionSites.id).asInstanceOf[js.Array[ConstructionSite]].headOption.foreach { site =>
        if (creep.build(site) == Errors.NotInRange.id) {
          creep.moveTo(site.pos)
        }
      }
    } else {
      harvest(creep)
    }

  }
}

