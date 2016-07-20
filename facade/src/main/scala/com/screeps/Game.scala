package com.screeps

import scala.scalajs.js

@js.native
trait CPU extends js.Object {
  def limit: Int = js.native
  def tickLimit: Int = js.native
  def bucket: Int = js.native

  /**
    * Get amount of CPU time used from the beginning of the current game tick.
    *
    * Always returns 0 in the Simulation mode.
    *
    * @return the currently used CPU time as a float number
    *
    * @example
    * {{{
    * if(Game.cpu.getUsed() > Game.cpu.tickLimit / 2) {
    *   console.log("Used half of CPU already!");
    * }
    * }}}
    *
    * @example
    * {{{
    * for(var name in Game.creeps) {
    *   var startCpu = Game.cpu.getUsed();
    *
    *   // creep logic goes here
    *
    *   var elapsed = Game.cpu.getUsed() - startCpu;
    *   console.log('Creep '+name+' has used '+elapsed+' CPU time');
    * }
    * }}}
    *
    */
  def getUsed() : Int = js.native
}

trait GlobalControlLevel {
  /** The current level. */
  def level: Int
  /** The current progress to the next level. */
  def progress: Int
  /** The progress required to reach the next level. */
  def progressTotal: Int
}

/**
  * The main game object containing all the gameplay information. The object is accessible via the global Game variable.
  */
@js.native
object Game extends js.Object {

  /** A hash containing all your construction sites with their id as hash keys. */
  val constructionSites: js.Dictionary[ConstructionSite] = js.native

  /** A hash containing all your construction sites with their id as hash keys. */
  val cpu: CPU = js.native

  /** A hash containing all your creeps with creep names as hash keys. */
  val creeps: js.Dictionary[Creep] = js.native

  /** A hash containing all your flags with flag names as hash keys. */
  val flags: js.Dictionary[Flag] = js.native

  /** Your <a href="http://support.screeps.com/hc/en-us/articles/203086021-Territory-control">GlobalControlLevel</a>, an object with the following properties */
  val gcl: GlobalControlLevel = js.native

  /** A global object representing world map. */
  val map: Map = js.native

  /**
    * A hash containing all the rooms available to you with room names as hash keys.
    * A room is visible if you have a creep or an owned structure in it.
    */
  val rooms: js.Dictionary[Room] = js.native

  /** A hash containing all your spawns with spawn names as hash keys. */
  val spawns: js.Dictionary[StructureSpawn] = js.native

  /** A hash containing all your structures with structure id as hash keys. */
  val structures: js.Dictionary[Structure] = js.native

  /**
    * System game tick counter. It is automatically incremented on every tick.
    *
    * {code}console.log(Game.time);{code}
    */
  def time: Int = js.native

  /**
    * Get an object with the specified unique ID. It may be a game object of any type. Only objects from the rooms which are visible to you can be accessed.
    * @param id The unique identificator.
    * @return Returns an object instance or null if it cannot be found.
    *
    * @example {{{
    *         creep.memory.sourceId = creep.pos.findClosestByRange(FIND_SOURCES).id
    *         var source = Game.getObjectById(creep.memory.sourceId);
    * }}}
    */
  def getObjectById(id: String): js.Object = js.native

  /**
    * Send a custom message at your profile email. This way, you can set up notifications to yourself on any occasion
    * within the game. You can schedule up to 20 notifications during one game tick.
    * Not available in the Simulation Room.
    *
    * @param message Custom text which will be sent in the message. Maximum length is 1000 characters.
    * @param groupInterval If set to 0 (default), the notification will be scheduled immediately. Otherwise, it will
    *                      be grouped with other notifications and mailed out later using the specified time in minutes.
    */
  def notify(message: String, groupInterval: Int): Unit = js.native

}