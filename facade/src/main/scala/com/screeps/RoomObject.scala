package com.screeps

import scala.scalajs.js

/**
  * Any object with a position in a room. Almost all game objects prototypes are derived from RoomObject.
  */
@js.native
trait RoomObject {
  /** An object representing the position of this object in the room. */
  val pos: RoomPosition = js.native

  /**
    * The link to the Room object. May be undefined in case if an object is a flag
    * or a construction site and is placed in a room that is not visible to you.
    */
  val room: Room = js.native
}
