package game.actors.enemies;

import game.utilities.Status;

/**
 * BigSlime enemy, created for creative mode (REQ 4, Assignment 3).
 * @version 1.1.1
 * @author sthi0011, lcha0068, esea0003
 */
public class BigSlime extends SlimeTroop {

    /**
     * Constructor for the BigSlime enemy that adds the capability status
     * BIG_SLIME (to ensure that it is not a SmallSlime enemy and will spawn
     * a SmallSlime enemy upon death.)
     */
    public BigSlime() {
        super("Big Slime", 'S', 100);
        this.addCapability(Status.BIG_SLIME);
    }
}