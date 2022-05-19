package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.grounds.WarpPipe;
import game.utilities.Status;

public class TeleportAction extends Action {

    private final Location location;
    private final Location target;
    private String newMapName;


    public TeleportAction(Location location, Location target){
        this.location = location;
        this.target = target;
        this.newMapName = "";
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

        return menuDescription(actor);
    }

//    public void setMapName(){
//        if (target.getGround().hasCapability(Status.MAIN_ISLAND)){
//            target.setGround(new WarpPipe(location, Status.LAVA_ZONE));
//            this.newMapName = "Main Island";
//        }else if (target.getGround().hasCapability(Status.LAVA_ZONE)){
//            target.setGround(new WarpPipe(location, Status.MAIN_ISLAND));
//            this.newMapName = "Lava Zone";
//        }
//    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " teleports to " + newMapName + "!";
    }
}
