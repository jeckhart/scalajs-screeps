package com.screeps.native

import scala.scalajs.js

/**
  * Launches a nuke to another room dealing huge damage to the landing area. Each launch has a cooldown and
  * requires energy and ghodium resources. Launching creates a Nuke object at the target room position which
  * is visible to any player until it is landed. Incoming nuke cannot be moved or cancelled. Nukes cannot be
  * launched from or to novice rooms.
  *
  * Controller level
  * 1-7	—
  * 8	1 nuke
  * Cost	100,000
  * Hits	1,000
  * Range	5 rooms
  * Launch cost	200,000 energy
  * 5,000 ghodium
  * Launch cooldown	100,000 ticks
  * Landing time	50,000 ticks
  * Effect	All creeps, construction sites and dropped resources in the room are removed immediately, even inside ramparts. Damage to structures:
  *   10,000,000 hits at the landing position;
  *   1,000,000 hits at range 1;
  *   100,000 hits at range 2-4.
  */
@js.native
trait StructureNuke extends OwnedStructureWithEnergy {

  /** The amount of ghodium containing in this structure. */
  val ghodium: Int = js.native

  /** The total amount of ghodium this structure can contain. */
  val ghodiumCapacity: Int = js.native

  /** The amount of game ticks until the next launch is possible. */
  val cooldown: Int = js.native

  /**
    * Launch a nuke to the specified position.
    * @param pos The target room position.
    * @return One of the following codes:
    *         OK - The operation has been scheduled successfully.
    *         NotOwner - You are not the owner of this structure.
    *         NotEnoughResources - The structure does not have enough energy and/or ghodium.
    *         InvalidTarget - The target is not a valid RoomPosition.
    *         NotInRange - The target room is out of range.
    *         Tired - The structure is still cooling down.
    *         RCLNotEnough - The Room Controller Level is not enough to use this link.
    */
  def launchNuke(pos: RoomPosition): Int = js.native

}
