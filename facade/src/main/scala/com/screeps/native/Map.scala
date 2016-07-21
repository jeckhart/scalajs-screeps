package com.screeps.native

import scala.scalajs.js


@js.native
trait RouteOptions extends js.Object {
  /**
    * This can be used to calculate the cost of entering that room.
    * You can use this to do things like prioritize your own rooms, or avoid some rooms.
    * You can return a floating point cost or Infinity to block the room.
    *
    * @param roomName the room we are considering on the route
    * @param fromRoomName the room from which we are leaving on the route
    * @return a floating point cost or Infinity to block the room.
    */
  def routeCallback(roomName: String, fromRoomName: String): Float
}


/** An object representing the world map. Use it to navigate between rooms.
  * The object is accessible via Game.map property.
  */
@js.native
trait Map extends js.Object {

  /**
    * List all exits available from the room with the given name.
    * @param roomName The room name.
    * @return The exits information in the following format, or null if the room not found.
    *         {{{   {
    *   "1": "W8N4",    // TOP
    *   "3": "W7N3",    // RIGHT
    *   "5": "W8N2",    // BOTTOM
    *   "7": "W9N3"     // LEFT
    * } }}}
    * @note CPU Cost: LOW
    */
  def describeExits(roomName: String): js.Object = js.native

  /**
    * Find the exit direction from the given room en route to another room.
    *
    * @param fromRoom Start room name or room object.
    * @param toRoom Finish room name or room object.
    * @param opts The route finding options. See [findRoute] and [RouteOptions].
    * @return The room direction constant, one of the following:
    *           Top, Right, Bottom, Left
    *         Or one of the following error codes:
    *           NoPath, InvalidArgs
    * @note CPU Cost: HIGH
    */
  def findExit(fromRoom: String, toRoom: String, opts: RouteOptions): Short = js.native

  /**
    * Find route from the given room to another room.
    * @param fromRoom Start room name or room object.
    * @param toRoom Finish room name or room object.
    * @param opts The route finding options. See [findRoute] and [RouteOptions].
    * @return The route array or NoPath
    */
  // Note: return should be js.Array[RoutePart]
  def findRoute(fromRoom: String, toRoom: String, opts: RouteOptions): js.Any = js.native

  /**
    * Get the linear distance (in rooms) between two rooms. You can use this function to estimate
    * the energy cost of sending resources through terminals, or using observers and nukes.
    * @param roomName1 the name of the first room
    * @param roomName2 the name of the second room
    * @return the number of rooms between the given two rooms
    */
  def getRoomLinearDistance(roomName1: String, roomName2: String): Int = js.native

  /**
    * Get terrain type at the specified room position. This method works
    * for any room in the world even if you have no access to it.
    * @param x X position in the room.
    * @param y Y position in the room.
    * @param roomName The room name.
    * @return One of the following string values:
    *         "plain", "swamp", "wall
    */
  def getTerrainAt(x: Int, y: Int, roomName: String): String = js.native

  /**
    * Check if the room with the given name is protected by temporary "newbie" walls.
    * @param roomName The room name
    * @return true if the room is protected
    */
  def isRoomProtected(roomName: String): Boolean = js.native
}
