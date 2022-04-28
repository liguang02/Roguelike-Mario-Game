package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.actors.Status;

public class SuperMushroom extends Item {

    public SuperMushroom() {
        super("SuperMushroom", 'p', true);
        this.addCapability(Status.TALL);
    }
}
