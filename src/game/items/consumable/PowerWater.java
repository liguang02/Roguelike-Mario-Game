package game.items.consumable;

import edu.monash.fit2099.engine.actors.Actor;
import game.items.consumable.Water;
import game.utilities.Status;

public class PowerWater extends Water {

    public PowerWater() {
        super("Power Water");
    }

    @Override
    public void consume(Actor actor) {
        actor.addCapability(Status.INTRINSIC_BUFF);
    }
}
