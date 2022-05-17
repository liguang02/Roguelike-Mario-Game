package game.actions.consumption;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.consumable.Consumable;
import game.utilities.Status;

/**
 * ConsumeAction to consume a Consumable Item from inventory and provide corresponding buffs according to req.
 * @version 1.0.0
 * @author sthi0011
 */
public class ConsumeAction extends Action {
    /**
     * Consumable type object that the entire action will work off.
     */
    private final Consumable consumableItem;

    /**
     * Constructor for ConsumeAction class
     * @param consumableItem A consumableItem type item
     */
    public ConsumeAction(Consumable consumableItem){
        this.consumableItem = consumableItem;
    }

    /**
     * Consumes the Consumable item present in actors inventory, does the specific actions such as adding capability and health buff based on requirement.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String description for actor consuming the item
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        consumableItem.consume(actor);
        return actor + " consumes " + consumableItem;
    }

    /**
     * Counts the number of items in players inventory to use for menu display
     * @param actor The actor performing the action.
     * @return int, count of number of items of specific type in actors inventory.
     */
    public int countItem(Actor actor){
        int count = 0;
        for(Item item : actor.getInventory()){
            if(item.toString().equals(consumableItem.toString()) && !item.hasCapability(Status.REMOVED)){
                count++;
            }
        }
        return count;
    }

    /**
     * Describe the action in a format suitable for displaying in the menu.
     * @param actor The actor performing the action.
     * @return Menu string for player to select the option to consume item from inventory
     */
    @Override
    public String menuDescription(Actor actor) {
        if(consumableItem.getLifeSpanString() != null){
            return actor + " consumes " + consumableItem + "(x" + countItem(actor) + ")" + " (Remaining life span: " + consumableItem.getLifeSpanString() + ")";
        }
        return actor + " consumes " + consumableItem + "(x" + countItem(actor) + ")" ;
    }
}
