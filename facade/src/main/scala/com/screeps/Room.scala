package com.screeps

import scala.scalajs.js

@js.native
object Room {
  /**
    * Serialize a path array into a short string representation, which is suitable to store in memory.
    * @param path A path array retrieved from Room.findPath
    * @return A serialized string form of the given path.
    * @example {{{var path = spawn.pos.findPathTo(source);
    *         Memory.path = Room.serializedPath(path);
    *         creep.moveByPath(Memory.path);}}}
    * @note CPU Cost: LOW
    */
  def serializePath(path: js.Array[RouteStep]): String = js.native

  /**
    * Deserialize a short string path representation into an array form.
    * @param path A serialized path string.
    * @return A path array.
    * @example {{{var path = Room.deserializePath(Memory.path);
    *         creep.moveByPath(path);}}}
    * @note CPU Cost: LOW
    */
  def deserializePath(path: String): js.Array[RouteStep] = js.native
}

/**
  * An object representing the room in which your units and structures are in.
  * It can be used to look around, find paths, etc. Every object in the room
  * contains its linked Room instance in the room property.
  */
@js.native
trait Room {


}

