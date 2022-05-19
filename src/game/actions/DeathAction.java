package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.SmallSlime;
import game.utilities.Status;

/**
 * DeathAction class to drop items and remove a dead actor from the map
 * @version 1.1.2
 * @author sthi0011, lcha0068, esea0003
 */
public class DeathAction extends Action {

    /**
     * Drops all actors inventory items ond death and removes it from the map
     * Assignment 3 creative mode: if the actor is a big slime and if it died, then it will spawn another SmallSlime.
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

        if (actor.hasCapability(Status.BIG_SLIME)){
            if(!actor.hasCapability(Status.REMOVED)) {
                Location place = map.locationOf(actor);
                map.removeActor(actor);
                map.addActor(new SmallSlime(), place);
                for (Exit exit : place.getExits()) {
                    Location surrounding = exit.getDestination();
                    if (!surrounding.containsAnActor()) {
                        surrounding.addActor(new SmallSlime());
                    }
                }
            }else{
                map.removeActor(actor);
            }
        } else{
            map.removeActor(actor);
        }
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
