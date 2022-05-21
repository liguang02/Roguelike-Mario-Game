package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.grounds.WarpPipe;

public class TeleportAction extends Action {
    /**
     * The location of the pipe that performs the teleportation
     */
    private final Location location;
    /**
     * The location of the target/destination pipe
     */
    private final Location target;
    /**
     * A string representing the map name of the target pipe is in
     */
    private final String newMap;
    /**
     * A string representing the map name of the source pipe is in
     */
    private final String currentMap;

    /**
     * Constructor for TeleportAction class
     * @param location The location of the pipe that performs the teleportation
     * @param target The location of the target/destination pipe
     * @param currentMap  A string representing the map name of the source pipe is in
     * @param newMap A string representing the map name of the target pipe is in
     */
    public TeleportAction(Location location, Location target, String currentMap, String newMap){
        this.location = location;
        this.target = target;
        this.currentMap = currentMap;
        this.newMap = newMap;
    }

    /**
     * Moves the actor to the target pipe location that is in the another game map and removes any piranha plant
     * if the target pipe has any. It will also create a new warp pipe on the target location to change the target and
     * source location of the warp pipe so that it can teleport back to the correct warp pipe at the same location.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String description for the actor teleporting between maps
     */
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

    /**
     * Describe the action appropriately in the menu.
     * @param actor The actor performing the action.
     * @return String description for the actor teleporting between maps
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " teleports to " + newMap;
    }
}
