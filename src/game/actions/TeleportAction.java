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
    private WarpPipe targetPipe;

    public TeleportAction(Location location, Location target, WarpPipe targetPipe){
//        this.actor = actor;
        this.location = location;
        this.target = target;
//        this.targetPipe = targetPipe;


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
//        targetPipe.setTarget(location);
//        targetPipe.setCurrentLoc(target);
        return actor + " teleports to " ;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " teleports ";
    }
}
