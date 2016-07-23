package com.screeps.native

import scala.scalajs.js

/**
  * Remotely attacks or heals creeps, or repairs structures. Can be targeted to any object in the room.
  * However, its effectiveness highly depends on the distance. Each action consumes energy.
  *
  * Controller level
  * 1-2	—
  * 3-4	1 tower
  * 5-6	2 towers
  * 7	3 towers
  * 8	6 towers
  *
  * Cost	5,000
  * Hits	3,000
  * Capacity	1,000
  * Energy per action	10
  *
  * Attack effectiveness	600 hits at range ≤5 to 150 hits at range ≥20
  * Heal effectiveness	400 hits at range ≤5 to 100 hits at range ≥20
  * Repair effectiveness	800 hits at range ≤5 to 200 hits at range ≥20
  */
@js.native
trait StructureTower extends OwnedStructureWithEnergy {

  /**
    * Remotely attack any creep in the room.
    * @param target The target of the attack
    * @return One of the following codes:
    *         OK - The operation has been scheduled successfully.
    *         NotEnoughResources - The tower does not have enough energy.
    *         InvalidTarget - The target is not a valid creep object.
    *         RCLNotEnough - The Room Controller Level is not enough to use this structure.
    * @note CPU Cost: CONST
    */
  def attack(target: Creep): Int = js.native

  /**
    * Remotely heal any creep in the room.
    * @param target The target creep object
    * @return One of the following codes:
    *         OK - The operation has been scheduled successfully.
    *         NotEnoughResources - The tower does not have enough energy.
    *         InvalidTarget - The target is not a valid creep object.
    *         RCLNotEnough - The Room Controller Level is not enough to use this structure.
    * @note CPU Cost: CONST
    */
  def heal(target: Creep): Int = js.native

  /**
    * Remotely repair any structure in the room.
    * @param target The target structure
    * @return One of the following codes:
    *         OK - The operation has been scheduled successfully.
    *         NotEnoughResources - The tower does not have enough energy.
    *         InvalidTarget - The target is not a valid creep object.
    *         RCLNotEnough - The Room Controller Level is not enough to use this structure.
    * @note CPU Cost: CONST
    */
  def repair(target: Structure): Int = js.native


}
