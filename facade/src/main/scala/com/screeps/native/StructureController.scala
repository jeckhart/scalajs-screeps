package com.screeps.native

import scala.scalajs.js

@js.native
trait ControllerReservation extends js.Object {
  /** The name of a player who reserved this controller. */
  val username: String = js.native
  /** The amount of game ticks when the reservation will end. */
  val ticksToEnd: Int = js.native
}

/**
  * Claim this structure to take control over the room.
  * The controller structure cannot be damaged or destroyed.
  * It can be addressed by Room.controller property.
  */
@js.native
trait StructureController extends OwnedStructure {

  /** Current controller level, from 0 to 8. */
  val level: Short = js.native

  /** The current progress of upgrading the controller to the next level. */
  val progress: Int = js.native

  /** The progress needed to reach the next level. */
  val progressTotal: Int = js.native

  /**
    * An object with the controller reservation info if present:
    */
  val reservation: ControllerReservation = js.native

  /**
    * The amount of game ticks when this controller will lose one level.
    * This timer can be reset by using Creep.upgradeController.
    */
  val ticksToDowngrade: Int = js.native

  /** The amount of game ticks while this controller cannot be upgraded due to attack. */
  val upgradeBlocked: Int = js.native

  /**
    * Make your claimed controller neutral again.
    * @return One of the following codes:
    *         OK - The operation has been scheduled successfully.
    *         NotOwner - You are not the owner of this controller.
    */
  def unclaim(): Int = js.native

}
