package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * DeathAction class to drop items and remove a dead actor from the map
 */
public class DeathAction extends Action {

    /**
     * Drops all actors inventory items ond death and removes it from the map
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String to show the actor is dead
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        ActionList dropActions = new ActionList();
        for (Item item : actor.getInventory())
            dropActions.add(item.getDropAction(actor));
        for (Action drop : dropActions)
            drop.execute(actor, map);
        map.removeActor(actor);
        return menuDescription(actor);
    }

    /**
     * Printing string of dead actor
     * @param actor The actor performing the action.
     * @return String of dead actor.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " is DEAD";
    }
}
