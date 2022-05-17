package game.items.consumable;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.consumption.ConsumeAction;
import game.items.Purchasable;
import game.utilities.Status;

public class FirePotion extends Item implements Purchasable, Consumable{
    private final int price;
    private final Action consumeAction = new ConsumeAction(this);

    public FirePotion(){
        super("Fire Potion", '6', false);
        this.price = 100;
        this.addToPurchasableManager();
    }

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
        if(actor.hasCapability(Status.CONSUMER_FIREPOT)){
            this.addAction(consumeAction);
            actor.removeCapability(Status.CONSUMER_FIREPOT);
        }
    }

    @Override
    public void consume(Actor actor) {
        actor.increaseMaxHp(50);
        actor.addCapability(Status.FIRE_IMMUNE);
        this.removeAction(consumeAction);
        actor.removeItemFromInventory(this);
    }
}
