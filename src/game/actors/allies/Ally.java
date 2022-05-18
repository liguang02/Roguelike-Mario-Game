package game.actors.allies;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Ally class extending Actor to set up NPCs.
 * @version 1.1.2
 * @author sthi0011, lcha0068, esea0003
 */
public abstract class Ally extends Actor {
    /**
     * Constructor for Allies which are NPCs in the game. These NPCs typically do not attack, wander or follow the
     * player unless the methods are overwritten.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Ally(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
    }
}
