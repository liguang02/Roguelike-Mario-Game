package game.items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Status;

public class PowerStar extends Item {

    private int lifeSpan;

    public PowerStar() {
        super("PowerStar", '*', true);
        this.addCapability(Status.INVINCIBLE);
        lifeSpan = 0;
    }

    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        if(currentLocation.getItems().contains(this) && lifeSpan >= 10){
            currentLocation.removeItem(this);
        }
        lifeSpan++;
    }
}
