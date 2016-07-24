package com.screeps.native

import scala.scalajs.js

/**
  * Remotely transfers energy to another Link in the same room.
  *
  * Controller level
  * 1-4	—
  * 5	2 links
  * 6	3 links
  * 7	4 links
  * 8	6 links
  *
  * Cost	5,000
  * Hits	1,000
  * Capacity	800
  * Cooldown time	1 tick per tile of the linear distance to the target
  * Energy loss	3%
  */
@js.native
trait StructureLink extends OwnedStructureWithEnergy {

  /** The amount of game ticks the link has to wait until the next transfer is possible. */
  val cooldown: Int = js.native

  /**
    * Remotely transfer energy to another link at any location in the same room.
    * @param target
    * @param amount
    * @return One of the following codes:
    *         OK - The operation has been scheduled successfully.
    *         NotOwner - You are not the owner of this link.
    *         NotEnoughResources - The structure does not have the given amount of energy.
    *         InvalidTarget - The target is not a valid StructureLink object.
    *         Full - The target cannot receive any more energy.
    *         InvalidArgs - The energy amount is incorrect.
    *         Tired - The link is still cooling down.
    *         RCLNotEnough - The Room Controller Level is not enough to use this link.
    * @note CPU Cost: CONST
    */
  def transferEnergy(target: StructureLink, amount: Int): Int = js.native

}
