package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

public class TeleportAction extends Action {
    private Actor actor;
    private Location location;
    private GameMap map;

    public TeleportAction(Actor actor, Location location, GameMap map){
        this.actor = actor;
        this.location = location;
        this.map = map;
        location.

    }
    @Override
    public String execute(Actor actor, GameMap map) {
        Location source = map.locationOf(actor);
        source.getGround().


        if (map.contains(actor)){
            map.moveActor(actor, location);
        }
        return actor + " teleports to " + map;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " teleports";
    }
}
