package com.screeps.api

import scala.scalajs.js

@js.native
trait ConstructionSite extends RoomObject {

  /** A unique object identificator. You can use Game.getObjectById method to retrieve an object instance by its id. */
  val id: String = js.native

  /** Whether this is your own construction site. */
  val my: Boolean = js.native

  /** The site's owner */
  val owner: Owner = js.native

  /** The current construction progress. */
  val progress: Int = js.native

  /** The total construction progress needed for the structure to be built. */
  val progressTotal: Int = js.native

  /** One of the STRUCTURE_* constants. */
  val structureType: StructureType.Value = js.native

  /** Remove the construction site. */
  def remove(): Errors.Value = js.native
}
