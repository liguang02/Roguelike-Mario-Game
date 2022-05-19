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
    private final String newMap;
    private final String currentMap;


    public TeleportAction(Location location, Location target, String currentMap, String newMap){
        this.location = location;
        this.target = target;
        this.currentMap = currentMap;
        this.newMap = newMap;
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
        target.setGround(new WarpPipe(location, currentMap, newMap));

        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " teleports to " + newMap + "!";
    }
}
