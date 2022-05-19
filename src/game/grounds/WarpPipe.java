package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.JumpAction;
import game.actions.TeleportAction;
import game.actors.enemies.PiranhaPlant;
import game.reset.Resettable;
import game.utilities.Status;

public class WarpPipe extends HighGround implements Resettable {
    /**
     * Name of the high ground, Warp pipe
     */
    private final String name;

    private int tickCounter;

    private Location target;

    private String targetMap;

    private String currentMap;

    /**
     * Constructor for WarpPipe class
     */
    public WarpPipe() {
        super('C', 100, 0);
        this.name = "Warp Pipe";
        tickCounter = 0;
        this.registerInstance();
    }

    public WarpPipe(Location target, String targetMap, String currentMap){
        super('C',100,0);
        name = "Warp Pipe";
        tickCounter = 0;
        this.target = target;
        this.targetMap = targetMap;
        this.currentMap = currentMap;
        this.registerInstance();
    }



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

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actionList = new ActionList();
        if (location.containsAnActor()){
            actionList.add(new TeleportAction(location, target, currentMap, targetMap));
            return actionList;
        }
        actionList.add(new JumpAction(100,0, direction, location));
        return actionList;
    }

    public void setTarget(Location target) {
        this.target = target;
    }


    @Override
    public boolean canActorEnter(Actor actor){
        return false;
    }

    public String toString(){
        return name;
    }

    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET);
    }
}
