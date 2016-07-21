package com.screeps.native

import scala.scalajs.js

/**
  * An object representing the specified position in the room. Every object in
  * the room contains RoomPosition as the pos property. The position object of
  * a custom location can be obtained using the Room.getPositionAt() method or
  * using the constructor.
  */
@js.native
trait RoomPosition extends js.Object {
  /** The name of the room. */
  val roomName: String = js.native
  /** X position in the room */
  val x: Int
  /** Y position in the room */
  val y: Int

  /**
    * Create new ConstructionSite at the specified location.
    * @param structureType One of the [StructureType]'s
    * @return One of the following codes:
    *         OK - The operation has been scheduled successfully.
    *         InvalidTarget -	The structure cannot be placed at the specified location.
    *         Full - You have too many construction sites. The maximum number of construction sites per player is 100.
    *         InvalidArgs - The location is incorrect.
    *         RCLNotEnough - The Room Controller Level is not enough. Learn more
    * @note CPU Cost: CONST
    */
  def createConstructionSite(structureType: String): Short = js.native

  /**
    * Create new Flag at the specified location.
    * @param name (optional) The name of a new flag. It should be unique, i.e. the Game.flags object should not contain
    *             another flag with the same name (hash key). If not defined, a random name will be generated
    * @param color (optional) The color of a new flag. Should be one of the COLOR_* constants. The default value is COLOR_WHITE
    * @param secondaryColor (optional) The secondary color of a new flag. Should be one of the COLOR_* constants. The default value is equal to color.
    * @return The name of a new flag, or one of the following error codes:
    *         NameExists - There is a flag with the same name already.
    *         InvalidArgs - The location or the color constant is incorrect.
    * @note CPU Cost: CONST
    */
  def createFlag(name: String = "", color: Color.Value = Color.White, secondaryColor: Color.Value = Color.White): js.Any = js.native

  /**
    * Find an object with the shortest path from the given position. Uses
    * <a href="http://en.wikipedia.org/wiki/A*_search_algorithm">A* search algorithm</a> and
    * <a href="http://en.wikipedia.org/wiki/Dijkstra">Dijkstra's algorithm</a>.
    * @param findType The type of object to find
    * @param opts An object containing pathfinding options:
    *               ignoreCreeps boolean - Treat squares with creeps as walkable. Can be useful with too many moving creeps around or in some other cases. The default value is false.
    *               ignoreDestructibleStructures boolean - Treat squares with destructible structures (constructed walls, ramparts, spawns, extensions) as walkable. Use this flag when you need to move through a territory blocked by hostile structures. If a creep with an ATTACK body part steps on such a square, it automatically attacks the structure. The default value is false.
    *               ignoreRoads boolean - Ignore road structures. Enabling this option can speed up the search. The default value is false. This is only used when the new PathFinder is enabled.
    *               costCallback function(string, CostMatrix) - You can use this callback to modify a CostMatrix for any room during the search. The callback accepts two arguments, roomName and costMatrix. Use the costMatrix instance to make changes to the positions costs. If you return a new matrix from this callback, it will be used instead of the built-in cached one. This option is only used when the new PathFinder is enabled.
    *               ignore array - An array of the room's objects or RoomPosition objects which should be treated as walkable tiles during the search. This option cannot be used when the new PathFinder is enabled (use costCallback option instead).
    *               avoid array - An array of the room's objects or RoomPosition objects which should be treated as obstacles during the search. This option cannot be used when the new PathFinder is enabled (use costCallback option instead).
    *               maxOps number - The maximum limit of possible pathfinding operations. You can limit CPU time used for the search based on ratio 1 op ~ 0.001 CPU. The default value is 2000.
    *               heuristicWeight number - Weight to apply to the heuristic in the A* formula F = G + weight * H. Use this option only if you understand the underlying A* algorithm mechanics! The default value is 1.2.
    *               serialize boolean - If true, the result path will be serialized using Room.serializePath. The default is false.
    *               maxRooms number - The maximum allowed rooms to search. The default (and maximum) is 16. This is only used when the new PathFinder is enabled.
    *             or one of the following:
    *               filter object, function, string
    *                 Only the objects which pass the filter using the Lodash.filter method will be used.
    *               algorithm string
    *                 One of the following constants:
    *                   astar is faster when there are relatively few possible targets;
    *                   dijkstra is faster when there are a lot of possible targets or when the closest target is nearby.
    *                 The default value is determined automatically using heuristics.
    * @return The closest object if found, null otherwise
    * @note CPU Cost: HIGH
    * @example {{{var target = creep.pos.findClosestByPath(FIND_MY_SPAWNS);
    *         creep.moveTo(target);}}}
    *         {{{var target = creep.pos.findClosestByPath(FIND_MY_SPAWNS, {maxOps: 500});
    *         creep.moveTo(target);}}}
    *         {{{var target = creep.pos.findClosestByPath(FIND_HOSTILE_CREEPS, {
    *           filter: function(object) {
    *             return object.getActiveBodyparts(ATTACK) == 0;
    *           }
    *         });}}}
    *         {{{var target = creep.pos.findClosestByPath(FIND_HOSTILE_CREEPS, {
    *           filter: { owner: { username: 'Invader' } }
    *         });}}}
    *         {{{var targets = [
    *           Game.creeps.John,
    *           Game.creeps.Mike,
    *           room.getPositionAt(10,10)
    *         ];
    *         var closest = creep.pos.findClosestByPath(targets);}}}
    */
  // TODO: Flesh out the opts type
  def findClosestByPath(findType: FindType.Value, opts: js.Object = ???): js.Object = js.native

  /**
    * Find an object with the shortest path from the given position. Uses
    * <a href="http://en.wikipedia.org/wiki/A*_search_algorithm">A* search algorithm</a> and
    * <a href="http://en.wikipedia.org/wiki/Dijkstra">Dijkstra's algorithm</a>.
    * @param objects An array of room's objects or RoomPosition objects that the search should be executed against.
    * @return The closest object if found, null otherwise
    * @note CPU Cost: HIGH
    * @example {{{var target = creep.pos.findClosestByPath(FIND_MY_SPAWNS);
    *         creep.moveTo(target);}}}
    *         {{{var target = creep.pos.findClosestByPath(FIND_MY_SPAWNS, {maxOps: 500});
    *         creep.moveTo(target);}}}
    *         {{{var target = creep.pos.findClosestByPath(FIND_HOSTILE_CREEPS, {
    *           filter: function(object) {
    *             return object.getActiveBodyparts(ATTACK) == 0;
    *           }
    *         });}}}
    *         {{{var target = creep.pos.findClosestByPath(FIND_HOSTILE_CREEPS, {
    *           filter: { owner: { username: 'Invader' } }
    *         });}}}
    *         {{{var targets = [
    *           Game.creeps.John,
    *           Game.creeps.Mike,
    *           room.getPositionAt(10,10)
    *         ];
    *         var closest = creep.pos.findClosestByPath(targets);}}}
    */
  // TODO: Flesh out the opts type
  def findClosestByPath(objects: js.Array[js.Object]): js.Object = js.native

  /**
    * Find an object with the shortest path from the given position. Uses
    * <a href="http://en.wikipedia.org/wiki/A*_search_algorithm">A* search algorithm</a> and
    * <a href="http://en.wikipedia.org/wiki/Dijkstra">Dijkstra's algorithm</a>.
    * @param objects An array of room's objects or RoomPosition objects that the search should be executed against.
    * @param opts An object containing pathfinding options:
    *               ignoreCreeps boolean - Treat squares with creeps as walkable. Can be useful with too many moving creeps around or in some other cases. The default value is false.
    *               ignoreDestructibleStructures boolean - Treat squares with destructible structures (constructed walls, ramparts, spawns, extensions) as walkable. Use this flag when you need to move through a territory blocked by hostile structures. If a creep with an ATTACK body part steps on such a square, it automatically attacks the structure. The default value is false.
    *               ignoreRoads boolean - Ignore road structures. Enabling this option can speed up the search. The default value is false. This is only used when the new PathFinder is enabled.
    *               costCallback function(string, CostMatrix) - You can use this callback to modify a CostMatrix for any room during the search. The callback accepts two arguments, roomName and costMatrix. Use the costMatrix instance to make changes to the positions costs. If you return a new matrix from this callback, it will be used instead of the built-in cached one. This option is only used when the new PathFinder is enabled.
    *               ignore array - An array of the room's objects or RoomPosition objects which should be treated as walkable tiles during the search. This option cannot be used when the new PathFinder is enabled (use costCallback option instead).
    *               avoid array - An array of the room's objects or RoomPosition objects which should be treated as obstacles during the search. This option cannot be used when the new PathFinder is enabled (use costCallback option instead).
    *               maxOps number - The maximum limit of possible pathfinding operations. You can limit CPU time used for the search based on ratio 1 op ~ 0.001 CPU. The default value is 2000.
    *               heuristicWeight number - Weight to apply to the heuristic in the A* formula F = G + weight * H. Use this option only if you understand the underlying A* algorithm mechanics! The default value is 1.2.
    *               serialize boolean - If true, the result path will be serialized using Room.serializePath. The default is false.
    *               maxRooms number - The maximum allowed rooms to search. The default (and maximum) is 16. This is only used when the new PathFinder is enabled.
    *             or one of the following:
    *               filter object, function, string
    *                 Only the objects which pass the filter using the Lodash.filter method will be used.
    *               algorithm string
    *                 One of the following constants:
    *                   astar is faster when there are relatively few possible targets;
    *                   dijkstra is faster when there are a lot of possible targets or when the closest target is nearby.
    *                 The default value is determined automatically using heuristics.
    * @return The closest object if found, null otherwise
    * @note CPU Cost: HIGH
    * @example {{{var target = creep.pos.findClosestByPath(FIND_MY_SPAWNS);
    *         creep.moveTo(target);}}}
    *         {{{var target = creep.pos.findClosestByPath(FIND_MY_SPAWNS, {maxOps: 500});
    *         creep.moveTo(target);}}}
    *         {{{var target = creep.pos.findClosestByPath(FIND_HOSTILE_CREEPS, {
    *           filter: function(object) {
    *             return object.getActiveBodyparts(ATTACK) == 0;
    *           }
    *         });}}}
    *         {{{var target = creep.pos.findClosestByPath(FIND_HOSTILE_CREEPS, {
    *           filter: { owner: { username: 'Invader' } }
    *         });}}}
    *         {{{var targets = [
    *           Game.creeps.John,
    *           Game.creeps.Mike,
    *           room.getPositionAt(10,10)
    *         ];
    *         var closest = creep.pos.findClosestByPath(targets);}}}
    */
  // TODO: Flesh out the opts type
  def findClosestByPath(objects: js.Array[js.Object], opts: js.Object): js.Object = js.native

  /**
    * Find an object with the shortest linear distance from the given position.
    * @param findType The type of object to find
    * @param opts An object containing one of the following:
    *             filter object, function, string
    *               Only the objects which pass the filter using the Lodash.filter method will be used.
    * @return The closest object if found, null otherwise
    * @note CPU Cost: AVERAGE
    * @example {{{var target = creep.pos.findClosestByRange(FIND_MY_SPAWNS);
    *         creep.moveTo(target);}}}
    *         {{{var target = creep.pos.findClosestByRange(FIND_HOSTILE_CREEPS, {
    *           filter: function(object) {
    *             return object.getActiveBodyparts(ATTACK) == 0;
    *           }
    *         });}}}
    *         {{{var target = creep.pos.findClosestByRange(FIND_HOSTILE_CREEPS, {
    *           filter: { owner: { username: 'Invader' } }
    *         });}}}
    *         {{{var targets = [
    *           Game.creeps.John,
    *           Game.creeps.Mike,
    *           room.getPositionAt(10,10)
    *         ];
    *         var closest = creep.pos.findClosestByRange(targets);}}}
    */
  // TODO: Flesh out the opts type
  def findClosestByRange(findType: FindType.Value, opts: js.Object = ???): js.Object = js.native

  /**
    * Find an object with the shortest linear distance from the given position.
    * @param objects An array of room's objects or RoomPosition objects that the search should be executed against.
    * @return The closest object if found, null otherwise
    * @note CPU Cost: AVERAGE
    * @example {{{var target = creep.pos.findClosestByRange(FIND_MY_SPAWNS);
    *         creep.moveTo(target);}}}
    *         {{{var target = creep.pos.findClosestByRange(FIND_HOSTILE_CREEPS, {
    *           filter: function(object) {
    *             return object.getActiveBodyparts(ATTACK) == 0;
    *           }
    *         });}}}
    *         {{{var target = creep.pos.findClosestByRange(FIND_HOSTILE_CREEPS, {
    *           filter: { owner: { username: 'Invader' } }
    *         });}}}
    *         {{{var targets = [
    *           Game.creeps.John,
    *           Game.creeps.Mike,
    *           room.getPositionAt(10,10)
    *         ];
    *         var closest = creep.pos.findClosestByRange(targets);}}}
    */
  // TODO: Flesh out the opts type
  def findClosestByRange(objects: js.Array[js.Object]): js.Object = js.native

  /**
    * Find an object with the shortest linear distance from the given position.
    * @param objects An array of room's objects or RoomPosition objects that the search should be executed against.
    * @param opts An object containing one of the following:
    *             filter object, function, string
    *               Only the objects which pass the filter using the Lodash.filter method will be used.
    * @return The closest object if found, null otherwise
    * @note CPU Cost: AVERAGE
    * @example {{{var target = creep.pos.findClosestByRange(FIND_MY_SPAWNS);
    *         creep.moveTo(target);}}}
    *         {{{var target = creep.pos.findClosestByRange(FIND_HOSTILE_CREEPS, {
    *           filter: function(object) {
    *             return object.getActiveBodyparts(ATTACK) == 0;
    *           }
    *         });}}}
    *         {{{var target = creep.pos.findClosestByRange(FIND_HOSTILE_CREEPS, {
    *           filter: { owner: { username: 'Invader' } }
    *         });}}}
    *         {{{var targets = [
    *           Game.creeps.John,
    *           Game.creeps.Mike,
    *           room.getPositionAt(10,10)
    *         ];
    *         var closest = creep.pos.findClosestByRange(targets);}}}
    */
  // TODO: Flesh out the opts type
  def findClosestByRange(objects: js.Array[js.Object], opts: js.Object): js.Object = js.native

  /**
    * Find all objects in the specified linear range.
    * @param findType The type of object to find
    * @param range The range distance
    * @param opts An object containing one of the following:
    *             filter object, function, string
    *               Only the objects which pass the filter using the Lodash.filter method will be used.
    * @return The closest object if found, null otherwise
    * @note CPU Cost: AVERAGE
    * @example {{{var target = creep.pos.findInRange(FIND_HOSTILE_CREEPS, 3);
    *         if(targets.length > 0) {
    *           creep.rangedAttack(targets[0]);
    *         } }}}
    *         {{{var targets = [
    *           Game.creeps.John,
    *           Game.creeps.Mike,
    *           room.getPositionAt(10,10)
    *         ];
    *         var closest = creep.pos.findInRange(targets, 3);}}}
    */
  // TODO: Flesh out the opts type
  def findInRange(findType: FindType.Value, range: Int, opts: js.Object = ???): js.Object = js.native

  /**
    * Find all objects in the specified linear range.
    * @param objects An array of room's objects or RoomPosition objects that the search should be executed against.
    * @param range The range distance
    * @return The closest object if found, null otherwise
    * @note CPU Cost: AVERAGE
    * @example {{{var target = creep.pos.findInRange(FIND_HOSTILE_CREEPS, 3);
    *         if(targets.length > 0) {
    *           creep.rangedAttack(targets[0]);
    *         } }}}
    *         {{{var targets = [
    *           Game.creeps.John,
    *           Game.creeps.Mike,
    *           room.getPositionAt(10,10)
    *         ];
    *         var closest = creep.pos.findInRange(targets, 3);}}}
    */
  // TODO: Flesh out the opts type
  def findClosestByRange(objects: js.Array[js.Object], range: Int): js.Object = js.native

  /**
    * Find all objects in the specified linear range.
    * @param objects An array of room's objects or RoomPosition objects that the search should be executed against.
    * @param range The range distance
    * @param opts An object containing one of the following:
    *             filter object, function, string
    *               Only the objects which pass the filter using the Lodash.filter method will be used.
    * @return The closest object if found, null otherwise
    * @note CPU Cost: AVERAGE
    * @example {{{var target = creep.pos.findInRange(FIND_HOSTILE_CREEPS, 3);
    *         if(targets.length > 0) {
    *           creep.rangedAttack(targets[0]);
    *         } }}}
    *         {{{var targets = [
    *           Game.creeps.John,
    *           Game.creeps.Mike,
    *           room.getPositionAt(10,10)
    *         ];
    *         var closest = creep.pos.findInRange(targets, 3);}}}
    */
  // TODO: Flesh out the opts type
  def findClosestByRange(objects: js.Array[js.Object], range: Int, opts: js.Object): js.Object = js.native

  /**
    * Find an optimal path to the specified position using
    * <a href="http://en.wikipedia.org/wiki/A*_search_algorithm">A* search algorithm</a>. This method is a shorthand
    * for Room.findPath. If the target is in another room, then the corresponding exit will be used as a target.
    * @param x X position in the room.
    * @param y Y position in the room.
    * @param opts An object containing additonal pathfinding flags:
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
    * @return An array with path steps
    * @note CPU Cost HIGH
    */
  def findPathTo(x: Int, y: Int, opts: js.Object = ???): js.Array[PathStep] = js.native

  /**
    * Find an optimal path to the specified position using
    * <a href="http://en.wikipedia.org/wiki/A*_search_algorithm">A* search algorithm</a>. This method is a shorthand
    * for Room.findPath. If the target is in another room, then the corresponding exit will be used as a target.
    * @param target Can be a RoomPosition object or any object containing RoomPosition.
    * @return An array with path steps
    * @note CPU Cost HIGH
    */
  def findPathTo(target: RoomPosition): js.Array[PathStep] = js.native

  /**
    * Find an optimal path to the specified position using
    * <a href="http://en.wikipedia.org/wiki/A*_search_algorithm">A* search algorithm</a>. This method is a shorthand
    * for Room.findPath. If the target is in another room, then the corresponding exit will be used as a target.
    * @param target Can be a RoomPosition object or any object containing RoomPosition.
    * @param opts An object containing additonal pathfinding flags:
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
    * @return An array with path steps
    * @note CPU Cost HIGH
    */
  def findPathTo(target: RoomPosition, opts: js.Object): js.Array[PathStep] = js.native

  /**
    * Get linear direction to the specified position.
    * @param x X position in the room.
    * @param y Y position in the room.
    * @return the direction to the point specified
    */
  def getDirectionTo(x:Int, y:Int): Direction.Value = js.native

  /**
    * Get linear direction to the specified position.
    * @param target Can be a RoomPosition object or any object containing RoomPosition.
    * @return the direction to the point specified
    */
  def getDirectionTo(target: RoomPosition): Direction.Value = js.native

  /**
    * Get linear range to the specified position.
    * @param x X position in the room.
    * @param y Y position in the room.
    * @return the number of squares to the given position
    */
  def getRangeTo(x:Int, y:Int): Int = js.native

  /**
    * Get linear range to the specified position.
    * @param target Can be a RoomPosition object or any object containing RoomPosition.
    * @return the number of squares to the given position
    */
  def getRangeTo(target: RoomPosition): Int = js.native

  /**
    * Check whether this position is in the given range of another position.
    * @param x X position in the same room.
    * @param y Y position in the same room.
    * @param range the range distance
    * @return true if the position is in range
    */
  def inRangeTo(x:Int, y:Int, range: Int): Boolean = js.native

  /**
    * Check whether this position is in the given range of another position.
    * @param target Can be a RoomPosition object or any object containing RoomPosition.
    * @param range the range distance
    * @return true if the position is in range
    */
  def inRangeTo(target: RoomPosition, range: Int): Boolean = js.native

  /**
    * Check whether this position is the same as the specified position.
    * @param x X position in the same room.
    * @param y Y position in the same room.
    * @return true if the position is in range
    */
  def isEqualTo(x:Int, y:Int): Boolean = js.native

  /**
    * Check whether this position is the same as the specified position.
    * @param target Can be a RoomPosition object or any object containing RoomPosition.
    * @return true if the position is in range
    */
  def isEqualTo(target: RoomPosition): Boolean = js.native

  /**
    * Check whether this position is on the adjacent square to the specified position. The same as inRangeTo(target, 1).
    * @param x X position in the same room.
    * @param y Y position in the same room.
    * @return true if the position is in range
    */
  def isNearTo(x:Int, y:Int): Boolean = js.native

  /**
    * Check whether this position is on the adjacent square to the specified position. The same as inRangeTo(target, 1).
    * @param target Can be a RoomPosition object or any object containing RoomPosition.
    * @return true if the position is in range
    */
  def isNearTo(target: RoomPosition): Boolean = js.native

  /**
    * Get the list of objects at the specified room position
    * @return An array with objects at the specified position in the following format:{{{
    *          [
    *            { type: 'creep', creep: {...} },
    *            { type: 'structure', structure: {...} },
    *            ...
    *            { type: 'terrain', terrain: 'swamp' }
    *          ]}}}
    */
  def look(): js.Array[js.Object] = js.native

  /**
    * Get the list of objects at the specified room position
    * @return An array of objects of the given type at the specified position if found.
    */
  def lookFor(lookType: LookType.Value): js.Array[js.Object] = js.native
}
