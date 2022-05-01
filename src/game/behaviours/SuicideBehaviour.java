package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Status;

public class SuicideBehaviour implements Behaviour{

    @Override
    public Action getAction(Actor actor, GameMap map) {
        if(actor.hasCapability(Status.REMOVED)){
            ActionList dropActions = new ActionList();
            for (Item item : actor.getInventory())
                dropActions.add(item.getDropAction(actor));
            for (Action drop : dropActions)
                drop.execute(actor, map);
            map.removeActor(actor);}
        return null;
    }
}
