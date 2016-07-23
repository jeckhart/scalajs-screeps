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