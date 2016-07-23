package com.screeps.native

import scala.scalajs.js

/**
  * A small container that can be used to store resources. This is a walkable structure.
  * All dropped resources automatically goes to the container at the same tile.
  *
  * Controller level	Any (including neutral rooms)
  * Available per room	5
  * Capacity	2,000
  * Cost	5,000
  * Hits	250,000
  * Decay	Loses 5,000 hits every 500 ticks in an owned room, and every 100 ticks in an unowned room.
  */
@js.native
trait StructureContainer extends OwnedStructureWithStorage {

}
