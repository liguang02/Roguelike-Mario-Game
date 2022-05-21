package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.JumpAction;
import game.actions.TeleportAction;
import game.actors.enemies.PiranhaPlant;
import game.reset.Resettable;
import game.utilities.Status;

/**
 * A WarpPipe object allows the player to teleport between maps and will spawn the Piranha Plant enemy
 * @version 1.1.4
 * @author lcha0068
 */
public class WarpPipe extends HighGround implements Resettable {
    /**
     * Name of the high ground, Warp pipe
     */
    private final String name;
    /**
     * A tick counter to track the number of turns the game is in
     */
    private int tickCounter;

    /**
     * the location of the target pipe/destination
     */
    private Location target;
    /**
     * A String containing the name of the map the target pipe/destination is in
     */
    private String targetMap;

    /**
     * A String containing the name of the map the source pipe is in
     */
    private String currentMap;

    /**
     * Default constructor for WarpPipe class
     * Will be called when creating the WarpPipe object in the bossMap (Lava Zone) as it doesnt have a fixed target/destination
     */
    public WarpPipe() {
        super('C', 100, 0);
        this.name = "Warp Pipe";
        tickCounter = 0;
        this.registerInstance();
    }

    /**
     * Overloading constructor for WarpPipe class
     * @param target The location of the target pipe/destination
     * @param targetMap A string containing the name of the map the target pipe is in
     * @param currentMap A string containing the name of the map the source pipe is in
     */
    public WarpPipe(Location target, String targetMap, String currentMap){
        super('C',100,0);
        name = "Warp Pipe";
        tickCounter = 0;
        this.target = target;
        this.targetMap = targetMap;
        this.currentMap = currentMap;
        this.registerInstance();
    }


    /**
     * This method will be called in every turn, it allows the WarpPipe to experience the time flow in the game
     * and is necessary for some operations such as only spawning the Piranha Plant enemy on the first turn after the game
     * initialization turn. Also it will ensure to spawn the Piranha Plant enemy if the player chooses to reset the game and
     * the current WarpPipe doesnt have any Piranha Plant on it.
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        tickCounter++;
        if (tickCounter==1 && !location.containsAnActor()){
            location.addActor(new PiranhaPlant());
        }
        if(this.hasCapability(Status.RESET)){
            if(!location.containsAnActor()){
                location.addActor(new PiranhaPlant());
            }
        }
    }

    /**
     * This will add the necessary actions corresponding to this object, TeleportAction when there is an actor(player) standing on it;
     * JumpAction if no actor is standing on it (we did not call the super() of this method from the parent class(HighGround) as it would check
     * for the PowerStar Capability, we would want the player still able to jump and teleport even if it has the Power Star status on.
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return An Actionlist containing TeleportAction or JumpAction
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actionList = new ActionList();
        if (location.containsAnActor()){
            actionList.add(new TeleportAction(location, target, currentMap, targetMap));
        }else {
            actionList.add(new JumpAction(100,0, direction, location));
        }
        return actionList;
    }


    /**
     * This method would return false as no actor should be able to walk through it(even with power star capability), the only way to
     * reach this ground is to perform a jump action.
     * @param actor the Actor to check
     * @return False to indicate no actor can walk through it
     */
    @Override
    public boolean canActorEnter(Actor actor){
        return false;
    }

    /**
     * A method that returns the name of the ground (Warp Pipe)
     * @return a string representing the name of the ground.
     */
    public String toString(){
        return name;
    }
    /**
     * resetInstance just adds RESET capability to the capability set of the Warp Pipe, so that it will do something accordingly when
     * the user resets the game (explained in tick().
     */
    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET);
    }
}
