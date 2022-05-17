package game.actions.pickups;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.consumption.ConsumeAction;
import game.items.consumable.Consumable;

/**
 * PickUpConsumeAction class to pick up and consume consumable items from the ground.
 * @version 1.0.0
 * @author sthi0011
 */
public class PickUpConsumeAction extends PickUpItemAction {
    /**
     * Consumable type object that the entire action will work off.
     */
    private final Consumable consumableItem;

    /**
     * Constructor for PickUpConsumeAction class
     * @param consumableItem A Consumable type item
     */
    public PickUpConsumeAction(Consumable consumableItem) {
        super(consumableItem.returnSelf());
        this.consumableItem = consumableItem;
    }

    /**
     * Picks up and consumes the Consumable item from the ground
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String description for actor consuming the consumable item.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        super.execute(actor, map);
        return new ConsumeAction(consumableItem).execute(actor, map);
    }

    /**
     * Describe the action in a format suitable for displaying in the menu.
     * @param actor The actor performing the action.
     * @return Menu string for player to select the option to pick up and consume Consumable item from ground.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " picks up and consumes " + consumableItem;
    }
}
