package game.items.consumable;

import edu.monash.fit2099.engine.actors.Actor;
import game.utilities.Status;

/**
 *
 */
public class PowerWater extends Water {
    /**
     * Constructor for PowerWater, just to set the name
     */
    public PowerWater() {
        super("Power Water");
    }

    /**
     * Consuming the water provides the consumer actor the INTRINSIC_BUFF capability
     * This buff is then consumed when the player uses an intrinsic attack providing a permanent damage increase.
     * @param actor The actor consuming the water.
     */
    @Override
    public void consume(Actor actor) {
        actor.addCapability(Status.INTRINSIC_BUFF);
    }
}
