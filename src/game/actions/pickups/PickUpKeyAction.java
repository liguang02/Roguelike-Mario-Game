package game.actions.pickups;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.permanent.Key;

import static game.utilities.Status.KEY;

/**
 * PickUpKeyAction to pick up the key dropped by Bowser.
 * @version 1.0.0
 * @author esea0003
 */
public class PickUpKeyAction extends PickUpItemAction {
    /**
     * The key object used for this action
     */
    private final Key key;

    /**
     * Action used to pick up the key to unlock the handcuffs for princess
     * @param key the key object
     */
    public PickUpKeyAction(Key key) {
        super(key);
        this.key = key;
    }

    /**
     * adds KEY capability to the player when the key is picked up
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return menuDescription method
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        super.execute(actor, map);
        actor.addCapability(KEY);
        return menuDescription(actor);
    }

    /**
     * Prints menu description to enable user to pick up the key
     * @param actor The actor performing the action.
     * @return menuOutput the menu description
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " picks up " + key;
    }
}
