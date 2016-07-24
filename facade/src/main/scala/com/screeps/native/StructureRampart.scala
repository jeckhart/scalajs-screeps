package com.screeps.native

import scala.scalajs.js

/**
  * Blocks movement of hostile creeps, and defends your creeps and structures on the same tile.
  * Can be used as a controllable gate.
  *
  * Controller level
  * 1	—
  * 2	300,000 max hits
  * 3	1,000,000 max hits
  * 4	3,000,000 max hits
  * 5	10,000,000 max hits
  * 6	30,000,000 max hits
  * 7	100,000,000 max hits
  * 8	300,000,000 max hits
  *
  * Cost	1
  * Hits when constructed	1
  * Decay	Loses 300 hits every 100 ticks
  */
@js.native
trait StructureRampart extends OwnedStructure {

  /** If false (default), only your creeps can step on the same square. If true, any hostile creeps can pass through. */
  val isPublic: Boolean = js.native

  /** The amount of game ticks when this rampart will lose some hit points. */
  val ticksToDecay: Int = js.native

  /**
    * Make this rampart public to allow other players' creeps to pass through
    * @param isPublic Whether this rampart should be public or non-public
    * @return One of the following codes:
    *         OK - The operation has been scheduled successfully.
    *         NotOwner - You are not the owner of this rampart.
    * @note CPU Cost: CONST
    */
  def makePublic(isPublic: Boolean): Int = js.native

}
