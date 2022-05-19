package game.items.permanent;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import game.actions.consumption.DrinkAction;
import game.items.consumable.Water;
import java.util.Stack;

/**
 * @version 1.0.0
 * @author sthi0011
 * A Bottle Item that cannot be dropped or picked up. An instance already in players inventory from start.
 */
public class Bottle extends Item {
    /**
     * Stack of water for actor to drink from
     */
    private final Stack<Water> bottle = new Stack<>();

    /**
     * Constructor for Bottle item
     */
    public Bottle() {
        super("Bottle", 'B', true);
        this.addAction(new DrinkAction(this));
    }

    /**
     * Overriding the getDropAction to return null (not allowed to drop this item)
     * @param actor The actor to add the action to
     * @return null;
     */
    @Override
    public DropItemAction getDropAction(Actor actor) {
        return null;
    }

    /**
     * Method to push a new Water object into the bottles stack.
     * @param liquid A water type object.
     */
    public void fill(Water liquid){
        bottle.push(liquid);
    }

    /**
     * Pops a water object form the top of the stack and returns it.
     * @return Water type object, null if stack empty
     */
    public Water drink(){
        if(!bottle.isEmpty()){
            return bottle.pop();
        }
        return null;
    }

    /**
     * Returns a string of the stack to display.
     * @return String of the bottle, stack contents.
     */
    public String bottleContents(){
        return bottle.toString();
    }
}
