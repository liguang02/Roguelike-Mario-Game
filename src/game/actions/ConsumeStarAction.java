package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Status;
import game.items.PowerStar;

/**
 * ConsumeStarAction to consume a Power Star in inventory and  provide corresponding buffs according to req.
 */
public class ConsumeStarAction extends Action {
    /**
     * PowerStar type object that the entire action will work off.
     */
    private final PowerStar powerStar;

    /**
     * Constructor for ConsumeStarAction class
     * @param powerStar A PowerStar type item
     */
    public ConsumeStarAction(PowerStar powerStar){
        this.powerStar = powerStar;
    }

    /**
     * Consumes the Super Mushroom item present in actors inventory, does the specific actions such as adding capability and health buff based on requirement.
     * Starts (resets) the countdown for power star buff via consumeStar() method.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String description for actor consuming the super mushroom
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.heal(200);
        actor.addCapability(Status.INVINCIBLE);
        powerStar.consumeStar();
        return menuDescription(actor);
    }

    public int countStar(Actor actor){
        int count = 0;
        for(Item item : actor.getInventory()){
            if(item.toString().equals(powerStar.toString()) && !item.hasCapability(Status.REMOVED)){
                count++;
            }
        }
        return count;
    }

    /**
     * Describe the action in a format suitable for displaying in the menu.
     * @param actor The actor performing the action.
     * @return Menu string for player to select the option to consume star from inventory
     */
    @Override
    public String menuDescription(Actor actor) {
            return actor + " consumes " + powerStar + "(x" + countStar(actor) + ")";
        }
}
