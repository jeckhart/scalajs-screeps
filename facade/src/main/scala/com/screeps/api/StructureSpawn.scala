package com.screeps.api

import scala.scalajs.js

/**
  * If the spawn is in process of spawning a new creep, this object will contain the new creep’s information.
  */
@js.native
trait Spawning extends js.Object {
  /** The name of a new creep. */
  val name: String = js.native

  /** Time needed in total to complete the spawning. */
  val needTime: Int = js.native

  /** Remaining time to go. */
  val remainingTime: Int = js.native
}

/**
  * Spawn is your colony center. This structure can create, renew, and recycle creeps.
  * All your spawns are accessible through Game.spawns hash list. Spawns auto-regenerate a little amount of
  * energy each tick, so that you can easily recover even if all your creeps died.
  *
  * Controller level
  * 1-6	1 spawn
  * 7	2 spawns
  * 8	3 spawns
  *
  * Cost	5,000
  * Hits	5,000
  * Capacity	300
  * Spawn time	3 ticks per each body part
  * Energy auto-regeneration	1 energy unit per tick while energy available in the room is less than 300
  */
@js.native
trait StructureSpawn extends OwnedStructure {

  /** The amount of energy containing in the spawn. */
  val energy: Int = js.native

  /** The total amount of energy the spawn can contain. */
  val energyCapacity: Int = js.native

  /** A shorthand to Memory.spawns[spawn.name]. You can use it for quick access the spawn’s specific memory data object.
    * [[http://support.screeps.com/hc/en-us/articles/203016642-Working-with-memory Learn more about memory]]
    */
  val memory: js.Any = js.native

  /**
    * Spawn’s name. You choose the name upon creating a new spawn, and it cannot be changed later.
    * This name is a hash key to access the spawn via the Game.spawns object.
    */
  val name: String = js.native

  /**
    * If the spawn is in process of spawning a new creep, this object
    * will contain the new creep’s information, or null otherwise.
    */
  val spawning: Spawning = js.native

  /**
    * Check if a creep can be created.
    * @param body An array describing the new creep’s body. Should contain 1 to 50 elements with one of these constants:
    *               WORK, MOVE, CARRY, ATTACK, RANGED_ATTACK, HEAL, TOUGH, CLAIM
    * @param name The name of a new creep. It should be unique creep name, i.e. the Game.creeps object should not
    *             contain another creep with the same name (hash key). If not defined, a random name will be generated.
    * @return One of the following codes:
    *         OK - The operation has been scheduled successfully.
    *         NotOwner - You are not the owner of this spawn.
    *         NameExists - There is a creep with the same name already
    *         Busy - The spawn is already in process of spawning another creep.
    *         NotEnoughResources - The spawn and its extensions contain not enough energy to create a creep with the given body.
    *         InvalidArgs - Body is not properly described.
    *         RCLNotEnough - The Room Controller Level is not enough.
    * @note CPU Cost: LOW
    */
  def canCreateCreep(body: js.Array[String], name: String = ""): Errors.Value = js.native

  /**
    * Start the creep spawning process. The required energy amount can be withdrawn
    * from all spawns and extensions in the room.
    * @param body An array describing the new creep’s body. Should contain 1 to 50 elements with one of these constants:
    *               WORK, MOVE, CARRY, ATTACK, RANGED_ATTACK, HEAL, TOUGH, CLAIM
    * @param name The name of a new creep. It should be unique creep name, i.e. the Game.creeps object should not
    *             contain another creep with the same name (hash key). If not defined, a random name will be generated.
    * @param memory The memory of a new creep. If provided, it will be immediately stored into Memory.creeps[name].
    * @return The name of the new creep or one of the following:
    *         OK - The operation has been scheduled successfully.
    *         NotOwner - You are not the owner of this spawn.
    *         NameExists - There is a creep with the same name already
    *         Busy - The spawn is already in process of spawning another creep.
    *         NotEnoughResources - The spawn and its extensions contain not enough energy to create a creep with the given body.
    *         InvalidArgs - Body is not properly described.
    *         RCLNotEnough - The Room Controller Level is not enough.
    * @note CPU Cost: CONST
    */
  def createCreep(body: js.Array[String], name: String = "", memory: js.Any = ???): js.Any = js.native

  /**
    * Kill the creep and drop up to 100% of resources spent on its spawning and boosting depending
    * on remaining life time. The target should be at adjacent square.
    * @param target The target creep object
    * @return The name of the new creep or one of the following:
    *         OK - The operation has been scheduled successfully.
    *         NotOwner - You are not the owner of the target creep.
    *         InvalidTarget - The specified target object is not a creep
    *         NotInRange - The target creep is too far away.
    * @note CPU Cost: CONST
    */
  def recycleCreep(target: Creep): Errors.Value = js.native

  /**
    * Increase the remaining time to live of the target creep. The target should be at adjacent square. The spawn
    * should not be busy with the spawning process. Each execution increases the creep's timer by amount of ticks
    * according to this formula: floor(600/body_size). Energy required for each execution is determined using this
    * formula: ceil(creep_cost/2.5/body_size). Renewing a creep removes all of its boosts.
    * @param target The target creep object
    * @return The name of the new creep or one of the following:
    *         OK - The operation has been scheduled successfully.
    *         NotOwner - You are not the owner of this spawn.
    *         Busy - The spawn is spawning another creep.
    *         NotEnoughResources - The spawn does not have enough energy.
    *         InvalidTarget - The specified target object is not a creep.
    *         Full - The target creep's time to live timer is full.
    *         NotInRange - The target creep is too far away.
    * @note CPU Cost: CONST
    */
  def renewCreep(target: Creep): Errors.Value = js.native
  
}
