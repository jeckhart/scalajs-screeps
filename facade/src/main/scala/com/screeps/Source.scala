package com.screeps

import scala.scalajs.js

/**
  * An energy source object. Can be harvested by creeps with a WORK body part.
  *
  * Energy amount          4500 in center rooms
  *                        3000 in an owned or reserved room
  *                        1500 in an unreserved room
  * Energy regeneration    Every 300 game ticks
  */
@js.native
trait Source extends RoomObject {

  /** The remaining amount of energy. */
  val energy: Int = js.native

  /** The total amount of energy in the source. */
  val energyCapacity: Int = js.native

  /** A unique object identificator. You can use Game.getObjectById method to retrieve an object instance by its id. */
  val id: String = js.native

  /** The remaining time after which the source will be refilled. */
  val ticksToRegeneration: Int = js.native
}
