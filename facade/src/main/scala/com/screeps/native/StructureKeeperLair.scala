package com.screeps.native

import scala.scalajs.js

/**
  * Non-player structure. Spawns NPC Source Keepers that guards energy sources and minerals in some rooms.
  * This structure cannot be destroyed.
  *
  * Spawning time	300
  */
@js.native
trait StructureKeeperLair extends OwnedStructure {

  /** Time to spawning of the next Source Keeper. */
  val ticksToSpawn: Int = js.native;

}
