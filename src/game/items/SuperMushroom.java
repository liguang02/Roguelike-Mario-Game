package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import game.actions.PickUpShroomAction;

/**
 * SuperMushroom class implementing the Super Mushroom item from Req. 4
 */
public class SuperMushroom extends Item {

    /**
     * Constructor for the SuperMushroom class
     */
    public SuperMushroom() {
        super("Super Mushroom", 'p', true);
    }

    /**
     * The specialized pickup item action for this item, SuperMushroom.
     * @param actor The actor performing the action.
     * @return new PickUpShroomAction that does pick up and consumption of the shroom when executed.
     */
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return new PickUpShroomAction(this);
    }

    /**
     * Overriding the default drop action as a super mushroom cannot be dropped according to Assignment Supplementary Videos, Episode 3.
     * @param actor The actor performing the action.
     * @return null
     */
    @Override
    public DropItemAction getDropAction(Actor actor) {
        return null;
    }
}
