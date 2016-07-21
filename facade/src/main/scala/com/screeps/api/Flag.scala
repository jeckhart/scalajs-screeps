package com.screeps.api

import scala.scalajs.js

/** A flag. Flags can be used to mark particular spots in a room. Flags are visible to their owners only */
@js.native
trait Flag extends RoomObject {

  /** Flag primary color. One of the COLOR_* constants. */
  val color: Color.Value = js.native

  /** A shorthand to Memory.flags[flag.name]. You can use it for quick access the flag's specific memory data object. */
  val memory: js.Any = js.native

  /**
    * Flag’s name. You can choose the name while creating a new flag, and it cannot be changed later.
    * This name is a hash key to access the spawn via the Game.flags object.
    */
  val name: String = js.native

  /** Flag secondary color. One of the COLOR_* constants. */
  val secondaryColor: Color.Value = js.native

  /**
    * Remove the flag.
    * @return OK
    */
  def remove(): Errors.Value = js.native

  /**
    * Set new color of the flag
    * @param color Primary color of the flag. One of the COLOR_* constants.
    * @param secondaryColor Secondary color of the flag. One of the COLOR_* constants.
    * @return One of the following codes:
    *         OK - The operation has been scheduled successfully.
    *         InvalidArgs - The color is not a valid color constant
    */
  def setColor(color: Color.Value, secondaryColor: Color.Value = ???): Errors.Value = js.native

  /**
    * Set new position of the flag.
    * @param x The X position in the room.
    * @param y The Y position in the room.
    * @return One of the following codes:
    *         OK - The operation has been scheduled successfully.
    *         InvalidTarget - The target provided is invalid.
    */
  def setPosition(x: Int, y: Int): Errors.Value = js.native

  /**
    * Set new position of the flag.
    * @param pos The position in the room.
    * @return One of the following codes:
    *         OK - The operation has been scheduled successfully.
    *         InvalidTarget - The target provided is invalid.
    */
  def setPosition(pos: RoomPosition): Errors.Value = js.native
}
