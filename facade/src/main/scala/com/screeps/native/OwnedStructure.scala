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
