package game.items.consumable;

import edu.monash.fit2099.engine.actors.Actor;
import game.items.consumable.Water;

public class HealingWater extends Water {

    public HealingWater() {
        super("Healing Water");
    }

    @Override
    public void consume(Actor actor) {
        actor.heal(50);
    }
}
