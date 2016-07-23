package com.screeps.native

import scala.scalajs.js

/**
  * Contains energy which can be spent on spawning bigger creeps. Extensions can be placed
  * anywhere in the room, any spawns will be able to use them regardless of distance.
  *
  * Controller level
  * 1	—
  * 2	5 extensions (50 capacity)
  * 3	10 extensions (50 capacity)
  * 4	20 extensions (50 capacity)
  * 5	30 extensions (50 capacity)
  * 6	40 extensions (50 capacity)
  * 7	50 extensions (100 capacity)
  * 8	60 extensions (200 capacity)
  *
  * Cost	3,000
  * Hits	1,000
  */
@js.native
trait StructureExtension extends OwnedStructureWithEnergy {

}