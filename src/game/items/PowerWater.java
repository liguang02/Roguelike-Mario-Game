package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import game.utilities.Status;

public class PowerWater extends Water{

    public PowerWater() {
        super("Power Water");
    }

    @Override
    public void consume(Actor actor) {
        actor.addCapability(Status.A_PERMANENT);
    }
}
