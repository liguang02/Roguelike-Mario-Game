package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Status;

public class SuicideBehaviour implements Behaviour{

    @Override
    public Action getAction(Actor actor, GameMap map) {
        if(actor.hasCapability(Status.REMOVED)){
            map.removeActor(actor);}
        return null;
    }
}
