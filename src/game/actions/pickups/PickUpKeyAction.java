package game.actions.pickups;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.permanent.Key;

import static game.utilities.Status.KEY;

public class PickUpKeyAction extends PickUpItemAction {
    /**
     * PowerStar type object that the entire action will work off.
     */
    private final Key key;

    /**
     */
    public PickUpKeyAction(Key key) {
        super(key);
        this.key = key;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        super.execute(actor, map);
        actor.addCapability(KEY);
        return menuDescription(actor);
    }
    @Override
    public String menuDescription(Actor actor) {
        String menuOutput = actor + " picks up " + key;
        return menuOutput;
    }
}
