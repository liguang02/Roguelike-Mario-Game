package game.items.consumable;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Abstract class Water to be extended by any consumable liquids (such as the one in the fountain)
 * @version 1.1.2
 * @author sthi0011
 */
public abstract class Water {
    /**
     * String of the name of the water
     */
    private final String name;

    /**
     * Constructor for the water, just ot set the name
     * @param name The string of the name of the water
     */
    public Water(String name) {
        this.name = name;
    }

    /**
     * Method returning the name of the water as string
     * @return String of the name of the water
     */
    public String toString(){
        return name;
    }

    /**
     * Abstract method to be implemented in each subclass.
     * Body of method should have what occurs to the actor when the actor consumes the water.
     * @param actor the actor consuming the water.
     */
    public abstract void consume(Actor actor);

}
