package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import game.actions.PickUpShroomAction;
import game.actors.Status;

public class SuperMushroom extends Item {

    public SuperMushroom() {
        super("Super Mushroom", 'p', true);
    }

    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return new PickUpShroomAction(this);
    }

    @Override
    public DropItemAction getDropAction(Actor actor) {
        return null;
    }
}
