package game.grounds.fountains;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.pickups.FillAction;
import game.items.permanent.Bottle;
import game.items.BottleManager;
import game.items.consumable.Water;
import java.util.Stack;

/**
 * @version 1.0.0
 * @author sthi0011
 * A fountain abstract class to be extended by any fountain.
 */
public abstract class Fountain extends Ground {
    /**
     *Name of the fountain
     */
    private final String name;

    /**
     * All the water in the fountain
     */
    private final Stack<Water> fountain;

    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public Fountain(char displayChar, String name) {
        super(displayChar);
        this.name = name;
        this.fountain = new Stack<>();
    }

    /**
     * A method that return the name of the tree. E.g. Mature, Sprout, Sapling
     * @return a string representing the name of the tree object
     */
    public String toString(){
        return name;
    }

    /**
     * Method to check what water the fountain is holding
     * @return String, name of the water
     */
    public String fountainLiquid(){
        return fountain.peek().toString();
    }

    /**
     * Method to provide allowable actions for the actor on location.
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return action list, list of allowable actions
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actionList = super.allowableActions(actor, location, direction);
        if(location.getActor() == actor) {
            Bottle actorsBottle = BottleManager.getInstance().getBottle(actor);
            if (actorsBottle != null && !fountain.isEmpty()) {
                actionList.add(new FillAction(actorsBottle, this));
            }
        }
        return actionList;
    }

    /**
     * Pops a water object from the stack
     * @return the water object popped
     */
    public Water removeWater(){
        return fountain.size() > 0 ? fountain.pop() : null;
    }

    /**
     * Refilling the fountain to have 10 water objects.
     * @param water The type of water we want to fill the fountain with
     */
    public void fillFountain(Water water){
        while (fountain.size() < 10){
            fountain.push(water);
        }
    }
}
