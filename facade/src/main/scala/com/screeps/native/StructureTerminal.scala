package com.screeps.native

import scala.scalajs.js

/**
  * Sends any resources to a Terminal in another room. The destination Terminal can belong to any
  * player. If its storage is full, the resources are dropped on the ground. Each transaction
  * requires additional energy (regardless of the transfer resource type) according to this
  * formula: ceil(0.1 * amount * linearDistanceBetweenRooms). For example, sending 100 mineral
  * units from W1N1 to W2N3 will consume 20 energy units. You can track your incoming and outgoing
  * transactions and estimate range cost between rooms using the Game.market object. Only one
  * Terminal per room is allowed that can be addressed by Room.terminal property.
  *
  * Required Controller Level - At least 6
  * Cost	                    - 100,000
  * Hits	                    - 3,0000
  * Capacity	                - 300,000
  */
@js.native
trait StructureTerminal extends OwnedStructureWithStorage {

  /**
    * Sends resource to a Terminal in another room with the specified name.
    * @param resourceType The type of resource to send
    * @param amount The amount of resources to be sent. The minimum amount is 100.
    * @param destination The name of the target room. You don't have to gain visibility in this room.
    * @param description The description of the transaction. It is visible to the recipient.
    *                    The maximum length is 100 characters.
    * @return One of the following codes:
    *         OK - The operation has been scheduled successfully.
    *         NotOwner - You are not the owner of this structure.
    *         NotEnoughResources - The structure does not have the required amount of resources.
    *         InvalidArgs - The arguments provided are incorrect.
    */
  def send(resourceType: String,
           amount: Int,
           destination: String,
           description: String = ""
          ): Int = js.native


}
