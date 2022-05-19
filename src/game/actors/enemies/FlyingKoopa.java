package game.actors.enemies;

import game.utilities.Status;

/**
 * The Flying Koopa enemy. The Flying Koopa is able to fly above any ground when it follows the actor or
 * wanders on the map. This is implemented in the respective Behaviours.
 *
 * @version 1.1.2
 * @author sthi0011, lcha0068, esea0003
 */
public class FlyingKoopa extends KoopaTroop {
    /**
     * A constructor to generate an instance of a Flying Koopa which has a SuperMushroom in its inventory
     * to be dropped when the Koopa is destroyed.
     */
    public FlyingKoopa() {
        super("FlyingKoopa", 'F', 150);
        this.addCapability(Status.FLYING);
    }
}
