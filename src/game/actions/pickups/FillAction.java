package game.actions.pickups;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.grounds.fountains.Fountain;
import game.items.permanent.Bottle;
import game.items.consumable.Water;

/**
 * @version 1.0.0
 * @author sthi0011
 * A FillAction for the player to fill their bottle at a fountain.
 */
public class FillAction extends Action {
    /**
     * The bottle of the actor to be filled.
     */
    private final Bottle bottle;
    /**
     * The fountain bottle is being filled from.
     */
    private final Fountain fountain;

    /**
     * Constructor for FillAction
     * @param bottle The bottle of the actor to be filled.
     * @param fountain The fountain bottle is being filled from.
     */
    public FillAction(Bottle bottle, Fountain fountain) {
        this.bottle = bottle;
        this.fountain = fountain;
    }

    /**
     * Filling the actors bottle and returning a string.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String of fill action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String returnString = menuDescription(actor);
        Water water = fountain.contents();
        bottle.fill(water);
        return returnString;
    }

    /**
     *
     * @param actor The actor performing the action.
     * @return The menu description of this fill action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " fills " + bottle + " with " + fountain.contents();
    }
}
