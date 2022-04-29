package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.PickUpCoinAction;
import game.actors.Status;
import game.reset.Resettable;

public class Coin extends Item implements Resettable {

    private int value;

    public Coin(int value) {
        super("Coin", '$', true);
        this.value = value;
        registerInstance();
    }
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return new PickUpCoinAction(this);
    }

    public int getValue() {
        return value;
    }


    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        if(this.hasCapability(ItemCapabilities.REMOVED)){
            currentLocation.removeItem(this);
        }

    }

    @Override
    public void resetInstance() {
        this.addCapability(ItemCapabilities.REMOVED);
    }
}
