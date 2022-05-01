package game.actors;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 */
public enum Status {
    HOSTILE_TO_ENEMY, // use this status to be considered hostile towards enemy (e.g., to be attacked by enemy)
    TALL, // use this status to tell that current instance has "grown".
    INVINCIBLE, //use this status to tell that current instance is invincible
    REMOVED, //use this status for reset, if an object has this status it should be removed from the map.
    RESET, //use this to check if player has done a reset, if reset occur add to capability set of player
    ENEMY, //use this to check if the actor is an enemy or not
    FERTILE, //use this to check whether if the ground is fertile(such as dirt)
    MONEY, //use this to identify a valuable item (such as coin)
    DORMANT, // if enemies like Koopa are dormant (unable to attack etc)
    CONSUMER_SHROOM, //To denote if the actor can consume mushrooms
    CONSUMER_STAR, //To denote if the actor can consume power stars
    WRENCH, //To denote the item is a wrench
    SHELL //To denote the actor becomes a shell on death
}
