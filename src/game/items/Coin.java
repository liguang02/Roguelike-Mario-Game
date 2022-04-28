package game.items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.reset.Resettable;

public class Coin extends Item implements Resettable {

    private int value;

    public Coin(int value) {
        super("Coin", '$', true);
        this.value = value;
        registerInstance();
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
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
