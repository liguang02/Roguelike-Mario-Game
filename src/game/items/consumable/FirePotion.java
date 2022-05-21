package game.items.consumable;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.consumption.ConsumeAction;
import game.items.Purchasable;
import game.utilities.Status;

public class FirePotion extends Item implements Purchasable, Consumable{
    /**
     * An instance variable that stores the price value for Power Star
     */
    private final int price;

    /**
     * The action used to give player option to consume the star, when item is consumed remove this action from allowable actions.
     */
    private final Action consumeAction = new ConsumeAction(this);

    /**
     * Constructor for FirePotion class
     */
    public FirePotion(){
        super("Fire Potion", '6', true);
        this.price = 60;
        this.addToPurchasableManager();
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
    /**
     * Method that provides fire immunity to the actor and also removes actions/the item from actors inventory
     * @param actor The actor that is going to be consuming the item
     */
    @Override
    public void consume(Actor actor) {
        actor.increaseMaxHp(50);
        actor.addCapability(Status.FIRE_IMMUNE);
        this.removeAction(consumeAction);
        actor.removeItemFromInventory(this);
    }

    /**
     * price method (a getter method to get the price of this item object)
     * Used in PurchaseAction where it needs to get the prices of the purchasableItem
     */
    public int price(){
        return price;
    }
}
