package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.grounds.WarpPipe;

public class TeleportAction extends Action {
//    private Actor actor;
    private Location location;
    private Location target;

    public TeleportAction(Location location, Location target){
        this.location = location;
        this.target = target;


    }
    @Override
    public String execute(Actor actor, GameMap map) {

        if (target.containsAnActor()){
            Actor piranhaPlant = target.getActor();
            target.map().removeActor(piranhaPlant);
        }
        if (map.contains(actor) && !target.containsAnActor()){
            map.moveActor(actor, target);
        }
        target.setGround(new WarpPipe(location));

        return actor + " teleports to " ;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " teleports ";
    }
}
