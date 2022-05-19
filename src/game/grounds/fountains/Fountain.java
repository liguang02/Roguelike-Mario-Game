package game.grounds.fountains;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.pickups.FillAction;
import game.items.BottleManager;
import game.items.consumable.Water;
import game.items.permanent.Bottle;

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
     * The water in this fountain
     */
    private final Water water;

    /**
     * Constructor.
     * @param displayChar character to display for this type of terrain
     * @param name name of fountain
     * @param water water type available in fountain
     */
    public Fountain(char displayChar, String name, Water water) {
        super(displayChar);
        this.name = name;
        this.water = water;
    }

    /**
     * A method that return the name of the tree. E.g. Mature, Sprout, Sapling
     * @return a string representing the name of the tree object
     */
    public String toString(){
        return name;
    }

    /**
     * Method to return the water in the fountain
     * @return Water type, water in fountain
     */
    public Water contents(){
        return water;
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
            if (actorsBottle != null) {
                actionList.add(new FillAction(actorsBottle, this));
            }
        }
        return actionList;
    }
}
