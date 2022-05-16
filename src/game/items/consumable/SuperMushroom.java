package game.items.consumable;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.consumption.ConsumeShroomAction;
import game.actions.pickups.PickUpShroomAction;
import game.items.Purchasable;
import game.utilities.Status;

/**
 * @version 1.1.2
 * @author sthi0011, lcha0068, esea0003
 * SuperMushroom class implementing the Super Mushroom item from Req. 4
 */
public class SuperMushroom extends Item implements Purchasable {
    /**
     * To store the price of the super mushroom
     */
    private final int price;
    /**
     *
     */
    private final Action consumeAction = new ConsumeShroomAction(this);
    /**
     * Constructor for the SuperMushroom class
     */
    public SuperMushroom() {
        super("Super Mushroom", '^', true);
        this.price = 400;
        this.addToPurchasableManager();
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
     * Every turn that the item is in the actors inventory, gives menu prompt to allow actor to consume it. (new item count of this item in inventory for the menu)
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);
        this.removeAction(consumeAction);
        if(actor.hasCapability(Status.CONSUMER_SHROOM)){
            this.addAction(consumeAction);
            actor.removeCapability(Status.CONSUMER_SHROOM);
        }
    }

    /**
     * Overriding the default drop action as a super mushroom cannot be dropped according to Assignment Supplementary Videos, Episode 3.
     * Drop only allowed for non-player actors.
     * @param actor The actor performing the action.
     * @return null
     */
    @Override
    public DropItemAction getDropAction(Actor actor) {
        if(!actor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            return super.getDropAction(actor);
        }
        return null;
    }

    /**
     * When mushroom consumed, removing consumeAction from action list.
     */
    public void consumeShroom(){
        this.removeAction(consumeAction);
    }
}