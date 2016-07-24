package com.screeps.native

import scala.scalajs.js

/**
  * Provides visibility into a distant room from your script.
  *
  *
  * Controller level
  * 1-7	—
  * 8	1 observer
  * Cost	8,000
  * Hits	500
  * Range	5 rooms
  */
@js.native
trait StructureObserver extends OwnedStructure {

  /**
    * Provide visibility into a distant room from your script. The target room object will be available on the next tick
    * @param roomName The name of the target room.
    * @return One of the following codes:
    *         OK - The operation has been scheduled successfully.
    *         InvalidArgs - roomName argument is not a valid room name value.
    *         RCLNotEnough - The Room Controller Level is not enough to use this structure.
    * @note CPU Cost: CONST
    */
  def observeRoom(roomName: String): Int = js.native

}
