package game.items.consumable;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;

/**
 * @version 1.1.2
 * @author sthi0011, lcha0068, esea0003
 * Interface for items that are consumable
 * It provides methods needed by the consumable items
 */
public interface Consumable {
    /**
     * consume method to implement, what occurs when the item is consumed.
     * @param actor the actor consuming the consumable
     */
    void consume(Actor actor);

    /**
     * If item has a life span in inventory to consume, return a string of its remaining life span else null
     * @return string of life span is applicable else null
     */
    default String getLifeSpanString(){
        return null;
    }

    /**
     * Instance of the consumable item as an Item instead of Consumable
     * @return Item type of the object.
     */
    default Item returnSelf(){
        return null;
    }
}
