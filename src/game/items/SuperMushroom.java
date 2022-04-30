package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import game.actions.ConsumeShroomAction;
import game.actions.PickUpShroomAction;

/**
 * SuperMushroom class implementing the Super Mushroom item from Req. 4
 */
public class SuperMushroom extends Item implements  Purchasable{
    /**
     * To store the price of the supermushroom
     */
    private int price;
    /**
     *
     */
    private final Action consumeAction = new ConsumeShroomAction(this);
    /**
     * Constructor for the SuperMushroom class
     */
    public SuperMushroom() {
        super("Super Mushroom", 'p', true);
        this.price = 400;
        this.addToPurchasableManager();
        this.addAction(consumeAction);
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
     * price method (a getter method to get the price of this item object)
     * Used in PurchaseAction where it needs to get the prices of the purchasableItem
     */
    public int price(){
        return price;
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

    public void consumeShroom(){
        this.removeAction(consumeAction);
    }
}
