package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Unlock Handcuffs Action that only appears when the player has a key capability.
 * @version 1.1.1
 * @author esea0003
 */
public class UnlockHandcuffsAction extends Action {

    /**
     * The player unlocks the handcuffs and the princess' monologue gets printed and returned.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the monologue that princess peach says before the game is over.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        menuDescription(actor);
        return "Thank you for saving me!";
    }

    /**
     * Menu description for unlocking the handcuffs.
     * @param actor The actor performing the action.
     * @return menu description.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " unlocks Princess Peach's Handcuffs.";
    }
}
