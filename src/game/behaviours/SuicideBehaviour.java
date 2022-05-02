package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.DeathAction;
import game.actors.Status;


/**
 * Class to do the removal of enemies (dead or reset) and drop items in their inventory.
 * @version 1.1.2
 * @author sthi0011, lcha0068, esea0003
 */
public class SuicideBehaviour implements Behaviour{

    /**
     * Used to remove the actor from the map and drop items from their inventory if they are killed (status REMOVED)
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return null
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {

        if(actor.hasCapability(Status.REMOVED)){
            return new DeathAction();
        }
        return null;
    }
}
