package com.screeps.api

import scala.scalajs.js

/**
  * The base prototype object of all structures.
  */
@js.native
trait Structure extends RoomObject {

  /** The current amount of hit points of the structure. */
  val hits: Int = js.native

  /** The total amount of hit points of the structure. */
  val hitsMax: Int = js.native

  /** A unique object identificator. You can use Game.getObjectById method to retrieve an object instance by its id. */
  val id: String = js.native

  /** The type of the structure, from the [StructureType] constants */
  val structureType: StructureType.Value = js.native

  /** Destroy this structure immediately. */
  def destroy(): Errors.Value = js.native

  /** Check whether this structure can be used. If the room controller level is not enough, then this
    * method will return false, and the structure will be highlighted with red in the game.
    */
  def isActive(): Boolean = js.native

  /**
    * Toggle auto notification when the structure is under attack.
    * The notification will be sent to your account email. Turned on by default
    * @param enabled Whether to enable notification or disable
    * @return One of the following codes:
    *         OK - The operation has been scheduled successfully.
    *         NotOwner - You are not the owner of this structure.
    *         InvalidArgs - enable argument is not a boolean value.
    */
  def notifyWhenAttacked(enabled: Boolean): Errors.Value = js.native

}
