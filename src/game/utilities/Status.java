package game.utilities;

/**
 * General enum class to add capabilities for the instances.
 * @version 1.1.2
 * @author sthi0011, lcha0068, esea0003
 */
public enum Status {
    HOSTILE_TO_ENEMY, // use this status to be considered hostile towards enemy (e.g., to be attacked by enemy)
    TALL, // use this status to tell that current instance has "grown".
    INVINCIBLE, //use this status to tell that current instance is invincible
    REMOVED, //use this status for reset, if an object has this status it should be removed from the map.
    RESET, //use this to check if player has done a reset, if reset occur add to capability set of player
    ENEMY, //use this to check if the actor is an enemy or not
    FERTILE, //use this to check whether if the ground is fertile(such as dirt)
    DORMANT, // if enemies like Koopa are dormant (unable to attack etc)
    CONSUMER_SHROOM, //To denote if the actor can consume mushrooms
    CONSUMER_STAR, //To denote if the actor can consume power stars
    WRENCH, //To denote the item is a wrench
    SHELL, //To denote the actor becomes a shell on death
    DEAD, //Status to denote the actor has been killed
    GENERIC, //Status for monologues with no specific conditions.
    KEY, //To denote if the item is a key (After defeating Bowser)
    FIRE, // to denote the ground is a lava that will inflict damage to player
    BURN, // For the fire ground class to cause harm to any player
    FLYING, // If the Koopa is a flying Koopa
    A_PERMANENT, //Status for permanent buff (fountain)
    CAN_FILL //Items that can store water from fountains
}
