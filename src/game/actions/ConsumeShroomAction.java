package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Status;
import game.items.SuperMushroom;

/**
 * ConsumeShroomAction to consume a Super Mushroom in inventory and  provide corresponding buffs according to req.
 */
public class ConsumeShroomAction extends Action {
    /**
     * SuperMushroom type object that the entire action will work off.
     */
    private final SuperMushroom superMushroom;

    /**
     * Constructor for ConsumeShroomAction class
     * @param superMushroom A superMushroom type item
     */
    public ConsumeShroomAction(SuperMushroom superMushroom) {
        this.superMushroom = superMushroom;
    }

    /**
     * Consumes the Super Mushroom item present in actors inventory, does the specific actions such as adding capability and health buff based on requirement.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String description for actor consuming the super mushroom
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.increaseMaxHp(50);
        actor.addCapability(Status.TALL);
        actor.removeItemFromInventory(superMushroom);
        superMushroom.consumeShroom();
        return actor + " consumes " + superMushroom;
    }

    /**
     * Counts the number of super mushroom in players inventory to use for menu display
     * @param actor The actor performing the action.
     * @return int, count of number of mushrooms in actors inventory.
     */
    public int countShroom(Actor actor){
        int count = 0;
        for(Item item : actor.getInventory()){
            if(item.toString().equals(superMushroom.toString())){
                count++;
            }
        }
        return count;
    }

    /**
     * Describe the action in a format suitable for displaying in the menu.
     * @param actor The actor performing the action.
     * @return Menu string for player to select the option to consume mushroom from inventory
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes " + superMushroom + "(x" + countShroom(actor) + ")" ;
    }
}
