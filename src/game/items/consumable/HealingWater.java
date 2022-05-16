package game.items.consumable;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * HealingWater object to fill the Healing Fountain.
 * @version 1.1.2
 * @author sthi0011
 */
public class HealingWater extends Water {
    /**
     * Constructor for the Healing Water, just to set the name
     */
    public HealingWater() {
        super("Healing Water");
    }

    /**
     * Consuming the water provides the consumer actor healing of 50hp
     * @param actor The actor consuming the water.
     */
    @Override
    public void consume(Actor actor) {
        actor.heal(50);
    }
}
