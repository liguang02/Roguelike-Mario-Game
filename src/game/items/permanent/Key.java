package game.items.permanent;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.utilities.Status;

/**
 * @version 1.0.0
 * @author sthi0011, lcha0068, esea0003
 * A class that represents the key item.
 */
public class Key extends Item {

    /**
     * Constructor for the key item
     */
    public Key() {
        super("Key", 'k', true);
    }

    /**
     * Adds capability KEY to the actor holding the key on tick
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);
        actor.addCapability(Status.KEY);
    }

    /**
     * Only allow drop action for enemies
     * @param actor Actor dropping the item
     * @return new drop action or null
     */
    @Override
    public DropItemAction getDropAction(Actor actor) {
        if(!actor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            return super.getDropAction(actor);
        }
        return null;
    }
}