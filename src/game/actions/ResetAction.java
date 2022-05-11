package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.reset.ResetManager;
import game.utilities.Status;

/**
 * resetAction class extending Action to give player option to reset.
 * @version 1.1.2
 * @author sthi0011, lcha0068, esea0003
 */
public class ResetAction extends Action {

    /**
     * Execute method, running the resetInstance, the resetManager runs
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String to denote that a reset has occurred.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        ResetManager resetInstance = ResetManager.getInstance();
        resetInstance.run();
        actor.addCapability(Status.RESET);
        return "Reset Occurred";
    }

    /**
     * Returns menu description to allow player to choose the reset.
     * @param actor The actor performing the action.
     * @return String for the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Reset the game.";
    }

    /**
     * Set the hotkey for this action always to r.
     * @return Hotkey character for reset
     */
    @Override
    public String hotkey() {
        return "r";
    }
}
