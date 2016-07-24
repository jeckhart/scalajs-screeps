package com.screeps.native

import scala.scalajs.js

/**
  * Produces mineral compounds from base minerals and boosts creeps. Learn more about minerals from this article.
  *
  * Controller level
  * 1-5	—
  * 6	3 labs
  * 7	6 labs
  * 8	10 labs
  *
  * Cost	50,000
  * Hits	500
  * Capacity	3000 mineral units, 2000 energy units
  *
  * Produce	10 mineral units per reaction
  * Reaction cooldown	10 ticks
  * Distance to input labs	2 squares
  * Boost cost	30 mineral units, 20 energy units per body part
  */
@js.native
trait StructureLab extends OwnedStructureWithEnergy {

  /** The amount of game ticks the lab has to wait until the next reaction is possible. */
  val cooldown: Int = js.native

  /** The amount of mineral resources containing in the lab. */
  val mineralAmount: Int = js.native

  /** The type of minerals containing in the lab. Labs can contain only one mineral type at the same time. */
  val mineralType: String = js.native

  /** The total amount of minerals the lab can contain. */
  val mineralCapacity: Int = js.native

  /**
    * Boosts creep body part using the containing mineral compound. The creep has to be at adjacent square to the lab.
    * @param creep The target creep.
    * @param bodyPartsCount The number of body parts of the corresponding type to be boosted. Body parts are always
    *                       counted left-to-right for TOUGH, and right-to-left for other types. If undefined, all the
    *                       eligible body parts are boosted.
    * @return One of the following codes:
    *         OK - The operation has been scheduled successfully.
    *         NotOwner - You are not the owner of this lab.
    *         NotFound - The mineral containing in the lab cannot boost any of the creep's body parts.
    *         NotEnoughResources - The lab does not have enough energy or minerals.
    *         InvalidTarget - The targets is not valid creep object.
    *         NotInRange - The targets are too far away.
    * @note CPU Cost: CONST
    */
  def boostCreep(creep: Creep, bodyPartsCount: Int = ???): Int = js.native

  /**
    * Produce mineral compounds using reagents from two another labs. The same input labs can be used by many output labs
    * @param lab1 The first source lab.
    * @param lab2 The second source lab.
    * @return One of the following codes:
    *         OK - The operation has been scheduled successfully.
    *         NotOwner - You are not the owner of this lab.
    *         NotEnoughResources - The source lab does not have enough resources or minerals.
    *         InvalidTarget - The targets are not valid lab objects.
    *         Full - The target cannot receive any more energy.
    *         NotInRange - The targets are too far away.
    *         InvalidArgs - The reaction cannot be run using this resources.
    *         Tired - The lab is still cooling down.
    * @note CPU Cost: CONST
    */
  def runReaction(lab1: StructureLab, lab2: StructureLab): Int = js.native
}
