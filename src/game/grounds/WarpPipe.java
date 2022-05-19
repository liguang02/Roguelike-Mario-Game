package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.TeleportAction;
import game.actors.enemies.PiranhaPlant;
import game.utilities.Status;

public class WarpPipe extends HighGround {
    /**
     * Name of the high ground, Warp pipe
     */
    private final String name;

    private int tickCounter;

    private Location target;



    /**
     * Constructor for WarpPipe class
     */
    public WarpPipe() {
        super('C', 100, 0);
        this.name = "Warp Pipe";
        tickCounter = 0;
        target = null;
    }
    public WarpPipe(Location target){
        super('C',100,0);
        name = "Warp Pipe";
        tickCounter = 0;
        this.target = target;

    }



    @Override
    public void tick(Location location) {
        super.tick(location);
        tickCounter++;
        if (tickCounter==1 && !location.containsAnActor()){
            location.addActor(new PiranhaPlant());
        }

    }


    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actionList = new ActionList();
        if (location.containsAnActor()){
            actionList.add(new TeleportAction(location, target));
        }
        actionList.add(super.allowableActions(actor, location, direction));

        return actionList;
    }

    public void setTarget(Location target) {
        this.target = target;
    }


    @Override
    public boolean canActorEnter(Actor actor){
        return !actor.hasCapability(Status.ENEMY);
    }

    public String toString(){
        return name;
    }

}
