package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeShroomAction;
import game.actions.ConsumeStarAction;
import game.actions.PickUpStarAction;
import game.actors.Status;
import game.reset.Resettable;

import java.util.List;

/**
 * PowerStar class implementing the Power Star item from Req. 4
 */
public class PowerStar extends Item implements Purchasable, Resettable {
    /**
     * The life span of the item on the ground/ time remaining of the players buff from this item
     */
    private int timeSpan;
    /**
     * If the player has consumed this item (gotten buffs from it) or not.
     * If not, could be either in the players inventory or left on the ground
     */
    private boolean consumed = false;
    /**
     * An instance variable that stores the price value for Power Star
     */
    private int price;
    /**
     * The action used to give player option to consume the star, when item is consumed remove this action from allowable actions.
     */
    private final Action consumeAction = new ConsumeStarAction(this);
    /**
     * Constructor for the PowerStar class
     * Setting the timeSpan to 10 (life span of item if left on ground)
     * Registering the item to be reset.
     */
    public PowerStar() {
        super("Power Star", '*', true);
        timeSpan = 10;
        this.registerInstance();
        this.price = 600;
        this.addToPurchasableManager();
        this.addAction(consumeAction);
    }

    /**
     * Tick method (called every turn when item is on ground)
     * Used to reduce the timeSpan by 1 and remove the item from the ground if not picked up by 10 turns
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        if(timeSpan <= 1){
            currentLocation.removeItem(this);}
        timeSpan--;
    }

    /**
     * Tick method (called every turn when item is in players inventory)
     * Used to reduce the timeSpan by 1 and remove all buffs from the player and the item from the inventory after 10 turns
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);
        if(consumed) {
            if (timeSpan >= 1) {
                //This line is needed for when power stars are picked up with overlapping time spans.
                actor.addCapability(Status.INVINCIBLE);
                timeSpan--;
            }else{
            actor.removeCapability(Status.INVINCIBLE);
            actor.removeItemFromInventory(this);
            }
        }
    }
    /**
     * price method (a getter method to get the price of this item object)
     * Used in PurchaseAction where it needs to get the prices of the purchasableItem
     */
    public int price(){
        return price;
    }

    /**
     * The specialized pickup item action for this item, PowerStar.
     * @param actor The actor performing the action.
     * @return new PickUpStarAction that does pick up and consumption of the star when executed.
     */
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return new PickUpStarAction(this);
    }

    /**
     * Overriding the default drop action as a power star cannot be dropped according to Assignment Supplementary Videos, Episode 3.
     * @param actor The actor performing the action.
     * @return null
     */
    @Override
    public DropItemAction getDropAction(Actor actor) {
        return null;
    }

    /**
     * Returns the current timeSpan of the Power Star item.
     * (If the item is on the ground, it is the remaining life span of the item before it disappears)
     * (If the item is in the actors inventory, it is the remaining time span of the buff before it dissapears)
     * @return the current timeSpan of the Power Star item.
     */
    public int getTimeSpan() {
        return timeSpan;
    }

    /**
     * Method that resets the timeSpan to the number of turns that the item buffs lasts according to the specification
     * Sets consumed to true (Item has been consumed by the actor) to carry out the specific actions in the tick method.
     * Removes the option to consume the item.
     */
    public void consumeStar(){
        timeSpan = 10;
        consumed = true;
        this.removeAction(consumeAction);
    }

    /**
     * Implementation of resetInstance() for the Power Stars, to remove the buff from the actor without adding it back in the next turn.
     * Sets the timeSpan to -1 for all consumed Power Stars, therefore on next turn the tick method removes the INVINCIBLE capability and the item from inventory (if it has not been removed already)
     */
    @Override
    public void resetInstance() {
        if(consumed) {
            timeSpan = -1;
        }
    }
}
