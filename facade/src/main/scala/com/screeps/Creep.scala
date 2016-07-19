package com.screeps

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName

@js.native
trait BodyPart extends js.Object{
  /**
    * If the body part is boosted, this property specifies the mineral type which is used for boosting.
    * One of the RESOURCE_* constants. [[http://support.screeps.com/hc/en-us/articles/207891075 Learn more]]
    */
  val boost: ResourceType.Value = js.native

  /** One of the body part types constants. */
  @JSName("type")
  val partType: String = js.native

  /** The remaining amount of hit points of this body part */
  val hits: Int = js.native
}

/**
  * Creeps are your units. Creeps can move, harvest energy, construct
  * structures, attack another creeps, and perform other actions. Each creep
  * consists of up to 50 body parts with the following possible types:
  *   Body part      Build cost   Effect per one body part
  *   MOVE           50           Decreases fatigue by 2 points per tick.
  *   WORK           100          Harvests 2 energy units from a source per tick.
  *                               Builds a structure for 5 energy units per tick.
  *                               Repairs a structure for 100 hits per tick consuming 1 energy unit per tick.
  *                               Dismantles a structure for 50 hits per tick returning 0.25 energy unit per tick.
  *                               Upgrades a controller for 1 energy unit per tick.
  *   CARRY          50           Can contain up to 50 resource units.
  *   ATTACK         80           Attacks another creep/structure with 30 hits per tick in a short-ranged attack.
  *   RANGED_ATTACK  150          Attacks another single creep/structure with 10 hits per tick in a long-range attack up to 3 squares long.
  *                               Attacks all hostile creeps/structures within 3 squares range with 1-4-10 hits (depending on the range).
  *   HEAL           250          Heals self or another creep restoring 12 hits per tick in short range or 4 hits per tick at a distance.
  *   CLAIM          600          Claims a neutral room controller.
  *                               Reserves a neutral room controller for 1 tick per body part.
  *                               Attacks a hostile room controller downgrade or reservation timer with 1 tick per 5 body parts.
  *                               A creep with this body part will have a reduced life time of 500 ticks and cannot be renewed.
  *   TOUGH          10           No effect, just additional hit points to the creep's body.
  */
@js.native
trait Creep extends RoomObject {
  /** An array describing the creep’s body. */
  val body: js.Array[BodyPart] = js.native

  /**
    * An object with the creep's cargo contents. Each object key is one of the RESOURCE_* constants,
    * values are resources amounts. Use lodash.sum to get the total amount of contents.
    */
  val carry: js.Dictionary[Int] = js.native

  /** The total amount of resources the creep can carry */
  val carryCapacity: Int = js.native

  /** The movement fatigue indicator. If it is greater than zero, the creep cannot move. */
  val fatigue: Int = js.native

  /** The current amount of hit points of the creep. */
  val hits: Int = js.native

  /** The maximum amount of hit points of the creep. */
  val hitsMax: Int = js.native

  /**
    * A unique object identifier. You can use [Game.getObjectById] method to retrieve an object instance by its id.
    */
  val id: String = js.native

  /**
    * A shorthand to Memory.creeps[creep.name]. You can use it for quick access the creep’s specific memory data object.
    */
  val memory: js.Any = js.native

  /** Whether it is your creep or foe. */
  val my: Boolean = js.native

  /**
    * The Creep’s name. You can choose the name while creating a new creep, and it cannot be changed later.
    * This name is a hash key to access the creep via the Game.creeps object.
    */
  val name: String = js.native

  /** The creep’s owner information */
  val owner: Owner = js.native

  /** Whether this creep is still being spawned. */
  val spawning: Boolean = js.native

  /** The remaining amount of game ticks after which the creep will die. */
  val ticksToLive: Int = js.native

  /**
    * Attack another creep or structure in a short-ranged attack. Requires the ATTACK body part. If the target is
    * inside a rampart, then the rampart is attacked instead. The target has to be at adjacent square to the creep.
    * If the target is a creep with ATTACK body parts and is not inside a rampart, it will automatically hit
    * back at the attacker.
    * @param target The target of the attack
    * @return One of the following codes:
    *         OK - The operation has been scheduled successfully.
    *         NotOwner - You are not the owner of this creep.
    *         Busy - The creep is still being spawned.
    *         InvalidTarget - The target is not a valid attackable object.
    *         NotInRange - The target is too far away.
    *         NoBodypart - There are no ATTACK body parts in this creep’s body.
    * @note CPU Cost: CONST
    */
  def attack(target: Creep): Errors.Value = js.native

  /**
    * Attack another creep or structure in a short-ranged attack. Requires the ATTACK body part. If the target is
    * inside a rampart, then the rampart is attacked instead. The target has to be at adjacent square to the creep.
    * If the target is a creep with ATTACK body parts and is not inside a rampart, it will automatically hit
    * back at the attacker.
    * @param target The target of the attack
    * @return One of the following codes:
    *         OK - The operation has been scheduled successfully.
    *         NotOwner - You are not the owner of this creep.
    *         Busy - The creep is still being spawned.
    *         InvalidTarget - The target is not a valid attackable object.
    *         NotInRange - The target is too far away.
    *         NoBodypart - There are no ATTACK body parts in this creep’s body.
    * @note CPU Cost: CONST
    */
  def attack(target: Structure): Errors.Value = js.native

  /**
    * Decreases the controller's downgrade or reservation timer for 1 tick per every 5 CLAIM body parts
    * (so the creep must have at least 5xCLAIM). The controller under attack cannot be upgraded for the
    * next 1,000 ticks. The target has to be at adjacent square to the creep.
    * @param target The target of the attack
    * @return One of the following codes:
    *         OK - The operation has been scheduled successfully.
    *         NotOwner - You are not the owner of this creep.
    *         Busy - The creep is still being spawned.
    *         InvalidTarget - The target is not a valid attackable object.
    *         NotInRange - The target is too far away.
    *         NoBodypart - There are not enough CLAIM body parts in this creep’s body.
    * @note CPU Cost: CONST
    */
  def attackController(target: Structure): Errors.Value = js.native

  /**
    * Build a structure at the target construction site using carried energy. Requires WORK and CARRY body parts.
    * The target has to be within 3 squares range of the creep.
    * @param target The target construction site to be built.
    * @return One of the following codes:
    *         OK - The operation has been scheduled successfully.
    *         NotOwner - You are not the owner of this creep.
    *         Busy - The creep is still being spawned.
    *         NotEnoughResources - The creep does not have any carried energy.
    *         InvalidTarget - The target is not a valid construction site object or the structure cannot be built
    *                         here (probably because of a creep at the same square).
    *         NotInRange - The target is too far away.
    *         NoBodypart - There are no WORK body parts in this creep’s body.
    *         RCLNotEnough - The Room Controller Level is not enough.
    * @note CPU Cost: CONST
    */
  def build(target: ConstructionSite): Errors.Value = js.native

  /**
    * Cancel the order given during the current game tick.
    * @param methodName The creep's method to cancel
    * @return NotFound if the specified name could not be found.
    * @note CPU Cost: NONE
    */
  def cancelOrder(methodName: String): Errors.Value = js.native

  /**
    * Claims a neutral controller under your control. Requires the CLAIM body part.
    * The target has to be at adjacent square to the creep
    * @param target The target controller object
    * @return One of the following codes:
    *         OK - The operation has been scheduled successfully.
    *         NotOwner - You are not the owner of this creep.
    *         Busy - The creep is still being spawned.
    *         NotEnoughResources - The creep does not have any carried energy.
    *         InvalidTarget - The target is not a valid neutral controller object.
    *         Full - You cannot claim more than 3 rooms in the Novice Area.
    *         NotInRange - The target is too far away.
    *         NoBodypart - There are no CLAIM body parts in this creep’s body.
    *         GCLNotEnough - The Global Control Level is not enough.
    * @note CPU Cost: CONST
    */
  def claimController(target: Structure): Errors.Value = js.native

  /**
    * Dismantles any (even hostile) structure returning 50% of the energy spent
    * on its repair. Requires the WORK body part. If the creep has an empty
    * CARRY body part, the energy is put into it; otherwise it is dropped on
    * the ground. The target has to be at adjacent square to the creep.
    * @param target The target structure
    * @return One of the following codes:
    *         OK - The operation has been scheduled successfully.
    *         NotOwner - You are not the owner of this creep.
    *         Busy - The creep is still being spawned.
    *         InvalidTarget - The target is not a valid creep object.
    *         Full - You cannot claim more than 3 rooms in the Novice Area.
    *         NotInRange - The target is too far away.
    *         NoBodypart - There are no WORK body parts in this creep’s body.
    * @note CPU Cost: CONST
    */
  def dismantle(target: Structure): Errors.Value = js.native

  /**
    * Build a structure at the target construction site using carried energy. Requires WORK and CARRY body parts.
    * The target has to be within 3 squares range of the creep.
    * @param resourceType One of the RESOURCE_* constants
    * @param amount The amount of resource units to be dropped. If omitted, all the available carried amount is used.
    * @return One of the following codes:
    *         OK - The operation has been scheduled successfully.
    *         NotOwner - You are not the owner of this creep.
    *         Busy - The creep is still being spawned.
    *         NotEnoughResources - The creep does not have the given amount of energy.
    * @note CPU Cost: CONST
    */
  def drop(resourceType: ResourceType.Value, amount: Int = ???): Errors.Value = js.native

  /**
    * Get the quantity of live body parts of the given type. Fully damaged parts do not count.
    * @param partType A body part type, one of the following body part constants:
    *                 MOVE, WORK, CARRY, ATTACK, RANGED_ATTACK, HEAL, TOUGH
    * @return A number representing the quantity of body parts.
    * @note CPU Cost: NONE
    */
  def getActiveBodyparts(partType: String): Int = js.native

  /**
    * Harvest energy from the source or minerals from the mineral deposit. Requires the WORK body part.
    * If the creep has an empty CARRY body part, the harvested resource is put into it; otherwise it is
    * dropped on the ground. The target has to be at an adjacent square to the creep.
    * @param target The object to be harvested
    * @return One of the following codes:
    *         OK - The operation has been scheduled successfully.
    *         NotOwner - You are not the owner of this creep.
    *         Busy - The creep is still being spawned.
    *         NotFound - Extractor not found. You must build an extractor structure to harvest minerals.
    *         NotEnoughResources - The target source does not contain any harvestable energy.
    *         InvalidTarget - The target is not a valid source object.
    *         NotInRange - The target is too far away.
    *         NoBodypart - There are no WORK body parts in this creep’s body.
    * @note CPU Cost: CONST
    */
  def harvest(target: Source): Errors.Value = js.native

  /**
    * Harvest energy from the source or minerals from the mineral deposit. Requires the WORK body part.
    * If the creep has an empty CARRY body part, the harvested resource is put into it; otherwise it is
    * dropped on the ground. The target has to be at an adjacent square to the creep.
    * @param target The object to be harvested
    * @return One of the following codes:
    *         OK - The operation has been scheduled successfully.
    *         NotOwner - You are not the owner of this creep.
    *         Busy - The creep is still being spawned.
    *         NotFound - Extractor not found. You must build an extractor structure to harvest minerals.
    *         NotEnoughResources - The target source does not contain any harvestable energy.
    *         InvalidTarget - The target is not a valid source object.
    *         NotInRange - The target is too far away.
    *         NoBodypart - There are no WORK body parts in this creep’s body.
    * @note CPU Cost: CONST
    */
  def harvest(target: Mineral): Errors.Value = js.native

  /**
    * Heal self or another creep. It will restore the target creep’s damaged body parts function and increase the
    * hits counter. Requires the HEAL body part. The target has to be at adjacent square to the creep.
    * @param target The target creep object
    * @return One of the following codes:
    *         OK - The operation has been scheduled successfully.
    *         NotOwner - You are not the owner of this creep.
    *         Busy - The creep is still being spawned.
    *         InvalidTarget - The target is not a valid creep object.
    *         NotInRange - The target is too far away.
    *         NoBodypart - There are no HEAL body parts in this creep’s body.
    * @note CPU Cost: CONST
    */
  def heal(target: Creep): Errors.Value = js.native

  /**
    * Move the creep one square in the specified direction. Requires the MOVE body part.
    * @param direction the direction to move
    * @return One of the following codes:
    *         OK - The operation has been scheduled successfully.
    *         NotOwner - You are not the owner of this creep.
    *         Busy - The creep is still being spawned.
    *         Tired - The fatigue indicator of the creep is non-zero.
    *         NoBodypart - There are no MOVE body parts in this creep’s body.    */
  def move(direction: Direction.Value): Errors.Value = js.native

  /**
    * Move the creep one square in the specified direction. Requires the MOVE body part.
    * @param path A path value as returned from Room.findPath, RoomPosition.findPathTo, or PathFinder.search methods.
    * @return One of the following codes:
    *         OK - The operation has been scheduled successfully.
    *         NotOwner - You are not the owner of this creep.
    *         Busy - The creep is still being spawned.
    *         NotFound - The specified path doesn't match the creep's location.
    *         InvalidArgs - path is not a valid path array
    *         Tired - The fatigue indicator of the creep is non-zero.
    *         NoBodypart - There are no MOVE body parts in this creep’s body.    */
  def moveByPath(path: String): Errors.Value = js.native

  /**
    * Move the creep one square in the specified direction. Requires the MOVE body part.
    * @param path A path value as returned from Room.findPath, RoomPosition.findPathTo, or PathFinder.search methods.
    * @return One of the following codes:
    *         OK - The operation has been scheduled successfully.
    *         NotOwner - You are not the owner of this creep.
    *         Busy - The creep is still being spawned.
    *         NotFound - The specified path doesn't match the creep's location.
    *         InvalidArgs - path is not a valid path array
    *         Tired - The fatigue indicator of the creep is non-zero.
    *         NoBodypart - There are no MOVE body parts in this creep’s body.    */
  def moveByPath(path: js.Array[PathStep]): Errors.Value = js.native

  /**
    * Find the optimal path to the target within the same room and move to it. A shorthand to
    * consequent calls of pos.findPathTo() and move() methods. If the target is in another room,
    * then the corresponding exit will be used as a target. Requires the MOVE body part
    * @param x X position of the target in the same room.
    * @param y Y position of the target in the same room.
    * @param opts An object containing additional options:
    *             reusePath number - This option enables reusing the path found along multiple game ticks. It allows to save CPU time, but can result in a slightly slower creep reaction behavior. The path is stored into the creep's memory to the _move property. The reusePath value defines the amount of ticks which the path should be reused for. The default value is 5. Increase the amount to save more CPU, decrease to make the movement more consistent. Set to 0 if you want to disable path reusing.
    *             serializeMemory boolean - If reusePath is enabled and this option is set to true, the path will be stored in memory in the short serialized form using Room.serializePath. The default value is true.
    *             noPathFinding boolean - If this option is set to true, moveTo method will return ERR_NOT_FOUND if there is no memorized path to reuse. This can significantly save CPU time in some cases. The default value is false.
    *             ignoreCreeps boolean - Treat squares with creeps as walkable. Can be useful with too many moving creeps around or in some other cases. The default value is false.
    *             ignoreDestructibleStructures boolean - Treat squares with destructible structures (constructed walls, ramparts, spawns, extensions) as walkable. Use this flag when you need to move through a territory blocked by hostile structures. If a creep with an ATTACK body part steps on such a square, it automatically attacks the structure. The default value is false.
    *             ignoreRoads boolean - Ignore road structures. Enabling this option can speed up the search. The default value is false. This is only used when the new PathFinder is enabled.
    *             costCallback function(string, CostMatrix) - You can use this callback to modify a CostMatrix for any room during the search. The callback accepts two arguments, roomName and costMatrix. Use the costMatrix instance to make changes to the positions costs. If you return a new matrix from this callback, it will be used instead of the built-in cached one. This option is only used when the new PathFinder is enabled.
    *             ignore array - An array of the room's objects or RoomPosition objects which should be treated as walkable tiles during the search. This option cannot be used when the new PathFinder is enabled (use costCallback option instead).
    *             avoid array - An array of the room's objects or RoomPosition objects which should be treated as obstacles during the search. This option cannot be used when the new PathFinder is enabled (use costCallback option instead).
    *             maxOps number - The maximum limit of possible pathfinding operations. You can limit CPU time used for the search based on ratio 1 op ~ 0.001 CPU. The default value is 2000.
    *             heuristicWeight number - Weight to apply to the heuristic in the A* formula F = G + weight * H. Use this option only if you understand the underlying A* algorithm mechanics! The default value is 1.2.
    *             serialize boolean - If true, the result path will be serialized using Room.serializePath. The default is false.
    *             maxRooms number - The maximum allowed rooms to search. The default (and maximum) is 16. This is only used when the new PathFinder is enabled.
    * @return One of the following codes:
    *         OK - The operation has been scheduled successfully.
    *         NotOwner - You are not the owner of this creep.
    *         Busy - The creep is still being spawned.
    *         Tired - The fatigue indicator of the creep is non-zero.
    *         NoBodypart - There are no MOVE body parts in this creep’s body.
    *         InvalidTarget - The target is not a valid creep object.
    *         NoPath - No path to the target could be found.
    * @note CPU Cost: HIGH
    */
  def moveTo(x: Int, y: Int, opts: js.Object): Errors.Value = js.native

  /**
    * Find the optimal path to the target within the same room and move to it. A shorthand to
    * consequent calls of pos.findPathTo() and move() methods. If the target is in another room,
    * then the corresponding exit will be used as a target. Requires the MOVE body part
    * @param target Can be a RoomPosition object or any object containing RoomPosition.
    *               The position doesn't have to be in the same room with the creep.
    * @param opts An object containing additional options:
    *             reusePath number - This option enables reusing the path found along multiple game ticks. It allows to save CPU time, but can result in a slightly slower creep reaction behavior. The path is stored into the creep's memory to the _move property. The reusePath value defines the amount of ticks which the path should be reused for. The default value is 5. Increase the amount to save more CPU, decrease to make the movement more consistent. Set to 0 if you want to disable path reusing.
    *             serializeMemory boolean - If reusePath is enabled and this option is set to true, the path will be stored in memory in the short serialized form using Room.serializePath. The default value is true.
    *             noPathFinding boolean - If this option is set to true, moveTo method will return ERR_NOT_FOUND if there is no memorized path to reuse. This can significantly save CPU time in some cases. The default value is false.
    *             ignoreCreeps boolean - Treat squares with creeps as walkable. Can be useful with too many moving creeps around or in some other cases. The default value is false.
    *             ignoreDestructibleStructures boolean - Treat squares with destructible structures (constructed walls, ramparts, spawns, extensions) as walkable. Use this flag when you need to move through a territory blocked by hostile structures. If a creep with an ATTACK body part steps on such a square, it automatically attacks the structure. The default value is false.
    *             ignoreRoads boolean - Ignore road structures. Enabling this option can speed up the search. The default value is false. This is only used when the new PathFinder is enabled.
    *             costCallback function(string, CostMatrix) - You can use this callback to modify a CostMatrix for any room during the search. The callback accepts two arguments, roomName and costMatrix. Use the costMatrix instance to make changes to the positions costs. If you return a new matrix from this callback, it will be used instead of the built-in cached one. This option is only used when the new PathFinder is enabled.
    *             ignore array - An array of the room's objects or RoomPosition objects which should be treated as walkable tiles during the search. This option cannot be used when the new PathFinder is enabled (use costCallback option instead).
    *             avoid array - An array of the room's objects or RoomPosition objects which should be treated as obstacles during the search. This option cannot be used when the new PathFinder is enabled (use costCallback option instead).
    *             maxOps number - The maximum limit of possible pathfinding operations. You can limit CPU time used for the search based on ratio 1 op ~ 0.001 CPU. The default value is 2000.
    *             heuristicWeight number - Weight to apply to the heuristic in the A* formula F = G + weight * H. Use this option only if you understand the underlying A* algorithm mechanics! The default value is 1.2.
    *             serialize boolean - If true, the result path will be serialized using Room.serializePath. The default is false.
    *             maxRooms number - The maximum allowed rooms to search. The default (and maximum) is 16. This is only used when the new PathFinder is enabled.
    * @return One of the following codes:
    *         OK - The operation has been scheduled successfully.
    *         NotOwner - You are not the owner of this creep.
    *         Busy - The creep is still being spawned.
    *         Tired - The fatigue indicator of the creep is non-zero.
    *         NoBodypart - There are no MOVE body parts in this creep’s body.
    *         InvalidTarget - The target is not a valid creep object.
    *         NoPath - No path to the target could be found.
    * @note CPU Cost: HIGH
    */
  def moveTo(target: RoomPosition, opts: js.Object): Errors.Value = js.native

  /**
    * Toggle auto notification when the creep is under attack. The notification will be sent to your account email.
    * Turned on by default
    * @param enabled Whether to enable notification or disable
    * @return One of the following codes
    *         OK - The operation has been scheduled successfully.
    *         NotOwner - You are not the owner of this creep.
    *         InvalidArgs - path is not a valid path array
    * @note CPU Cost: CONST
    */
  def notifyWhenAttacked(enabled: Boolean): Errors.Value = js.native

  /**
    * Pick up an item (a dropped piece of energy). Requires the CARRY body part.
    * The target has to be at adjacent square to the creep or at the same square
    * @param target The target object to be picked up.
    * @return One of the following codes:
    *         OK - The operation has been scheduled successfully.
    *         NotOwner - You are not the owner of this creep.
    *         Busy - The creep is still being spawned.
    *         InvalidTarget - The target is not a valid object to pickup.
    *         Full - The creep cannot receive any more energy.
    *         NotInRange - The target is too far away.
    *         NoBodypart - There are no CARRY body parts in this creep’s body.
    * @note CPU Cost: CONST
    */
  def pickup(target: Resource): Errors.Value = js.native

  /**
    * A ranged attack against another creep or structure. Requires the RANGED_ATTACK body part. If the target is
    * inside a rampart, the rampart is attacked instead. The target has to be within 3 squares range of the creep.
    * @param target The target object to be attacked.
    * @return One of the following codes:
    *         OK - The operation has been scheduled successfully.
    *         NotOwner - You are not the owner of this creep.
    *         Busy - The creep is still being spawned.
    *         InvalidTarget - The target is not a valid attackable object.
    *         NotInRange - The target is too far away.
    *         NoBodypart - There are no RANGED_ATTACK body parts in this creep’s body.
    * @note CPU Cost: HIGH
    */
  def rangedAttack(target: Creep): Errors.Value = js.native

  /**
    * A ranged attack against another creep or structure. Requires the RANGED_ATTACK body part. If the target is
    * inside a rampart, the rampart is attacked instead. The target has to be within 3 squares range of the creep.
    * @param target The target object to be attacked.
    * @return One of the following codes:
    *         OK - The operation has been scheduled successfully.
    *         NotOwner - You are not the owner of this creep.
    *         Busy - The creep is still being spawned.
    *         InvalidTarget - The target is not a valid attackable object.
    *         NotInRange - The target is too far away.
    *         NoBodypart - There are no RANGED_ATTACK body parts in this creep’s body.
    * @note CPU Cost: HIGH
    */
  def rangedAttack(target: Structure): Errors.Value = js.native

  /**
    * Heal another creep at a distance. It will restore the target creep’s damaged body parts function and increase
    * the hits counter. Requires the HEAL body part. The target has to be within 3 squares range of the creep.
    * @param target The target object to be healed.
    * @return One of the following codes:
    *         OK - The operation has been scheduled successfully.
    *         NotOwner - You are not the owner of this creep.
    *         Busy - The creep is still being spawned.
    *         InvalidTarget - The target is not a valid creep object.
    *         NotInRange - The target is too far away.
    *         NoBodypart - There are no HEAL body parts in this creep’s body.
    * @note CPU Cost: HIGH
    */
  def rangedHeal(target: Creep): Errors.Value = js.native

  /**
    * A ranged attack against all hostile creeps or structures within 3 squares range. Requires the RANGED_ATTACK
    * body part. The attack power depends on the range to each target. Friendly units are not affected.
    * @return One of the following codes:
    *         OK - The operation has been scheduled successfully.
    *         NotOwner - You are not the owner of this creep.
    *         Busy - The creep is still being spawned.
    *         NoBodypart - There are no RANGED_ATTACK body parts in this creep’s body.
    * @note CPU Cost: HIGH
    */
  def rangedMassAttack(): Errors.Value = js.native

  /**
    * Repair a damaged structure using carried energy. Requires the WORK and CARRY body parts.
    * The target has to be within 3 squares range of the creep.
    * @param target The target structure to be repaired
    * @return One of the following codes:
    *         OK - The operation has been scheduled successfully.
    *         NotOwner - You are not the owner of this creep.
    *         Busy - The creep is still being spawned.
    *         NotEnoughResources - The creep does not carry any energy.
    *         InvalidTarget - The target is not a valid creep object.
    *         NotInRange - The target is too far away.
    *         NoBodypart - There are no WORK body parts in this creep’s body.
    * @note CPU Cost: CONST
    */
  def repair(target: Structure): Errors.Value = js.native

  /**
    * Temporarily block a neutral controller from claiming by other players. Each tick, this command increases
    * the counter of the period during which the controller is unavailable by 1 tick per each CLAIM body part.
    * The maximum reservation period to maintain is 5,000 ticks. The target has to be at adjacent square to the creep.
    * @param target The target controller object to be reserved.
    * @return One of the following codes:
    *         OK - The operation has been scheduled successfully.
    *         NotOwner - You are not the owner of this creep.
    *         Busy - The creep is still being spawned.
    *         NotEnoughResources - The creep does not carry any energy.
    *         InvalidTarget - The target is not a valid neutral controller object.
    *         NotInRange - The target is too far away.
    *         NoBodypart - There are no CLAIM body parts in this creep’s body.
    * @note CPU Cost: CONST
    */
    def reserveController(target: Structure): Errors.Value = js.native

  /**
    * Display a visual speech balloon above the creep with the specified message. The message will disappear
    * after a few seconds. Useful for debugging purposes. Only the creep's owner can see the speech message.
    * @param message The message to be displayed. Maximum length is 10 characters.
    * @return One of the following codes:
    *         OK - The operation has been scheduled successfully.
    *         NotOwner - You are not the owner of this creep.
    *         Busy - The creep is still being spawned.
    * @note CPU Cost: CONST
    */
    def say(message: String): Errors.Value = js.native

  /**
    * Kill the creep immediately.
    * @return One of the following codes:
    *         OK - The operation has been scheduled successfully.
    *         NotOwner - You are not the owner of this creep.
    *         Busy - The creep is still being spawned.
    * @note CPU Cost: CONST
    */
  def suicide(): Errors.Value = js.native

  /**
    * Transfer resource from the creep to another object. The target has to be at adjacent square to the creep.
    * @param target The target object.
    * @param resourceType One of the RESOURCE_* constants.
    * @param amount The amount of resources to be transferred. If omitted, all the available carried amount is used.
    * @return One of the following codes:
    *         OK - The operation has been scheduled successfully.
    *         NotOwner - You are not the owner of this creep.
    *         Busy - The creep is still being spawned.
    *         NotEnoughResources - The creep does not have the given amount of resources.
    *         InvalidTarget - The target is not a valid object which can contain the specified resource.
    *         Full - The target cannot receive any more resources.
    *         NotInRange - The target is too far away.
    *         InvalidArgs - The resources amount is incorrect.
    * @note CPU Cost: CONST
    */
  def transfer(target: Creep, resourceType: ResourceType.Value, amount: Int = ???): Errors.Value = js.native

  /**
    * Transfer resource from the creep to another object. The target has to be at adjacent square to the creep.
    * @param target The target object.
    * @param resourceType One of the RESOURCE_* constants.
    * @return One of the following codes:
    *         OK - The operation has been scheduled successfully.
    *         NotOwner - You are not the owner of this creep.
    *         Busy - The creep is still being spawned.
    *         NotEnoughResources - The creep does not have the given amount of resources.
    *         InvalidTarget - The target is not a valid object which can contain the specified resource.
    *         Full - The target cannot receive any more resources.
    *         NotInRange - The target is too far away.
    *         InvalidArgs - The resources amount is incorrect.
    * @note CPU Cost: CONST
    */
  def transfer(target: Structure, resourceType: ResourceType.Value): Errors.Value = js.native

  /**
    * Transfer resource from the creep to another object. The target has to be at adjacent square to the creep.
    * @param target The target object.
    * @param resourceType One of the RESOURCE_* constants.
    * @param amount The amount of resources to be transferred. If omitted, all the available carried amount is used.
    * @return One of the following codes:
    *         OK - The operation has been scheduled successfully.
    *         NotOwner - You are not the owner of this creep.
    *         Busy - The creep is still being spawned.
    *         NotEnoughResources - The creep does not have the given amount of resources.
    *         InvalidTarget - The target is not a valid object which can contain the specified resource.
    *         Full - The target cannot receive any more resources.
    *         NotInRange - The target is too far away.
    *         InvalidArgs - The resources amount is incorrect.
    * @note CPU Cost: CONST
    */
  def transfer(target: Structure, resourceType: ResourceType.Value, amount: Int): Errors.Value = js.native

  /**
    * Upgrade your controller to the next level using carried energy. Upgrading controllers raises your Global Control
    * Level in parallel. Requires WORK and CARRY body parts. The target has to be within 3 squares range of the creep.
    * A fully upgraded level 8 controller can't be upgraded over 15 energy units per tick regardless of creeps
    * abilities. The cumulative effect of all the creeps performing upgradeController in the current tick is taken into
    * account. This limit can be increased by using ghodium mineral boost.
    * @param target The target object.
    * @return One of the following codes:
    *         OK - The operation has been scheduled successfully.
    *         NotOwner - You are not the owner of this creep.
    *         Busy - The creep is still being spawned.
    *         NotEnoughResources - The creep does not have any carried energy.
    *         InvalidTarget - The target is not a valid controller object.
    *         Full - The target cannot receive any more resources.
    *         NotInRange - The target is too far away.
    *         InvalidArgs - The resources amount is incorrect.
    *         NoBodypart - There are no WORK body parts in this creep’s body.
    * @note CPU Cost: CONST
    */
  def upgradeController(target: Structure): Errors.Value = js.native

  /**
    * Withdraw resources from a structure. The target has to be at adjacent square to the creep. Multiple creeps can
    * withdraw from the same structure in the same tick. Your creeps can withdraw resources from hostile structures
    * as well, in case if there is no hostile rampart on top of it.
    * @param target The target object.
    * @param resourceType One of the RESOURCE_* constants.
    * @param amount The amount of resources to be transferred. If omitted, all the available carried amount is used.
    * @return One of the following codes:
    *         OK - The operation has been scheduled successfully.
    *         NotOwner - You are not the owner of this creep.
    *         Busy - The creep is still being spawned.
    *         NotEnoughResources - The target does not have the given amount of resources.
    *         InvalidTarget - The target is not a valid object which can contain the specified resource.
    *         Full - The creep's carry is full.
    *         NotInRange - The target is too far away.
    *         InvalidArgs - The resource amount or type is incorrect
    * @note CPU Cost: CONST
    */
  def withdraw(target: Structure, resourceType: ResourceType.Value, amount: Int): Errors.Value = js.native

}
