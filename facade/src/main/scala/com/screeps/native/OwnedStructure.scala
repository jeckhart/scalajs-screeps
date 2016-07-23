package com.screeps.native

import scala.scalajs.js

@js.native
trait Owner extends js.Object {
  val username: String = js.native
}

@js.native
trait OwnedStructure extends Structure {

  /** Whether this is your own structure. */
  val my: Boolean = js.native

  /**
    * An object with the structure’s owner info.
    */
  val owner: Owner = js.native
}

@js.native
trait OwnedStructureWithEnergy extends OwnedStructure {

  /** The amount of energy containing in the spawn. */
  val energy: Int = js.native

  /** The total amount of energy the spawn can contain. */
  val energyCapacity: Int = js.native

}

@js.native
trait OwnedStructureWithStorage extends OwnedStructure {
  /**
    * An object with the storage contents. Each object key is one of the RESOURCE_* constants, values
    * are resources amounts. RESOURCE_ENERGY is always defined and equals to 0 when empty, other
    * resources are undefined when empty. You can use lodash.sum to get the total amount of contents.
    */
  val store: js.Dictionary[Int] = js.native

  /** The total amount of resources the storage can contain. */
  val storeCapacity: Int = js.native

}