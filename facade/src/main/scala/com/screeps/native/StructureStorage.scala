package com.screeps.native

import scala.scalajs.js

/**
  * A structure that can store huge amount of resource units. Only one structure
  * per room is allowed that can be addressed by Room.storage property.
  *
  * Required Controller Level - At least 4
  * Cost	                    - 30,000
  * Hits	                    - 10,000
  * Capacity	                - 1,000,000
  */
@js.native
trait StructureStorage extends OwnedStructureWithStorage {

}
