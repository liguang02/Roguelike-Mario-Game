package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.FillAction;
import game.items.Bottle;
import game.items.BottleManager;
import game.items.Water;
import java.util.Stack;

public abstract class Fountain extends Ground {
    /**
     *Name of the tree
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

    public String fountainLiquid(){
        return fountain.peek().toString();
    }

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

    public Water removeWater(){
        return fountain.size() > 0 ? fountain.pop() : null;
    }

    public void fillFountain(Water water){
        while (fountain.size() < 10){
            fountain.push(water);
        }
    }
}
