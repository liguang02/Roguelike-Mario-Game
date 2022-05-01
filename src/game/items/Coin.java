package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.PickUpCoinAction;
import game.actors.Status;
import game.reset.ResetManager;
import game.reset.Resettable;

public class Coin extends Item implements Resettable {

    private final int value;

    public Coin(int value) {
        super("Coin", '$', true);
        this.value = value;
        registerInstance();
    }
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        //Removing the reset from ResetManager if coin already in inventory.
        ResetManager.getInstance().cleanUp(this);
        return new PickUpCoinAction(this);
    }

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
