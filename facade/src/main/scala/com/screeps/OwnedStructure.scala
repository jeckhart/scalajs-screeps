package com.screeps

import scala.scalajs.js

@js.native
trait OwnedStructure extends Structure {

  @js.native trait Owner {
    val username: String = js.native
  }

  /** Whether this is your own structure. */
  val my: Boolean = js.native

  /**
    * An object with the structure’s owner info.
    */
  val owner: Owner = js.native
}
