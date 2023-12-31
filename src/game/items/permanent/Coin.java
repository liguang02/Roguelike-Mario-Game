package game.items.permanent;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.pickups.PickUpCoinAction;
import game.utilities.Status;
import game.reset.ResetManager;
import game.reset.Resettable;

/**
 * @version 1.1.2
 * @author sthi0011, lcha0068, esea0003
 * Coin class is a subclass of item class. Coin can be picked up by the player actor and add the coin value
 * into the player's wallet balance.
 */

public class Coin extends Item implements Resettable {
    /**
     * A fixed int value as each coin object can only have 1 fixed value
     */

    private final int value;

    /**
     * A constructor for coin class
     * @param value the coin value
     */
    public Coin(int value) {
        super("Coin", '$', true);
        this.value = value;
        registerInstance();
    }

    /**
     * Overrides the method in item class to return a PickUpCoinAction instead  of
     * PickUpItemAction.
     * @param actor the actor that will pick up the coin/item
     * @return PickUpCoinAction, which is a subclass of PickUpItemAction
     */
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        //Removing the reset from ResetManager if coin already in inventory.
        ResetManager.getInstance().cleanUp(this);
        return new PickUpCoinAction(this);
    }

    /**
     * A getter method to get the value of the coin
     * @return value of the coin in integer
     */
    public int getValue() {
        return value;
    }

    /**
     * Removing the coin from the location if reset occurs (REMOVED status in capability set)
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        if(this.hasCapability(Status.REMOVED)){
            currentLocation.removeItem(this);
        }
    }

    /**
     * Adds capability REMOVED when called, reset occurs.
     */
    @Override
    public void resetInstance() {
        this.addCapability(Status.REMOVED);
    }
}
