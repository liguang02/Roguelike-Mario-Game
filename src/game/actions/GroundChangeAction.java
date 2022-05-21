package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.grounds.Fire;
import game.utilities.Status;

/**
 * Action to change the ground surrounding the actor. (All exits)
 * @version 1.0.0
 * @author sthi0011
 */
public class GroundChangeAction extends Action {
    /**
     * The ground to set all exits to.
     */
    private final Ground newGround;

    /**
     * Constructor for the GroundChangeAction
     * @param newGround Ground to be set
     */
    public GroundChangeAction(Ground newGround) {
        this.newGround = newGround;
    }

    /**
     * Placing all exits to be the new ground type.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String of ground change action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();

            if(newGround.hasCapability(Status.FIRE)){
                destination.setGround(new Fire());
            }else{
                destination.setGround(newGround);
            }
        }

        return menuDescription(actor);
        }

    /**
     * Printing menu description string of this action
     * @param actor The actor performing the action.
     * @return String for ground change action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " sets surrounding ground to be " + newGround;
    }
}
