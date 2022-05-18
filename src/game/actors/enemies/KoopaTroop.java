package game.actors.enemies;

import game.actors.enemies.Enemy;
import game.utilities.Status;

/**
 * The KoopaTroop is an abstract class that extends Enemy which is used by the Koopas.
 * @version 1.1.2
 * @author sthi0011, lcha0068, esea0003
 */
abstract class KoopaTroop extends Enemy {
    /**
     * Constructor for the generated Koopas. The Koopas have the capability of Shell in common.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public KoopaTroop(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.SHELL);
    }
}
