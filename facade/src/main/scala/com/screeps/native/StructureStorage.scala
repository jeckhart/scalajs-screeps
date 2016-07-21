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
trait StructureStorage extends OwnedStructure {

  /**
    * An object with the storage contents. Each object key is one of the RESOURCE_* constants, values
    * are resources amounts. RESOURCE_ENERGY is always defined and equals to 0 when empty, other
    * resources are undefined when empty. You can use lodash.sum to get the total amount of contents.
    */
  val store: js.Object = js.native

  /** The total amount of resources the storage can contain. */
  val storeCapacity: Int = js.native

}
