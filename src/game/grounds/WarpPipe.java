package game.grounds;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.TeleportAction;
import game.actors.PiranhaPlant;

public class WarpPipe extends HighGround {
    /**
     * Name of the high ground, Warp pipe
     */
    private final String name;
    private int tickCounter;

    /**
     * Constructor for WarpPipe class
     */
    public WarpPipe() {
        super('C', 100, 0);
        this.name = "Warp Pipe";
        tickCounter = 0;
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
            actionList.add(new TeleportAction(actor, location, location.map()));
        }
        actionList.add(super.allowableActions(actor, location, direction));

        return actionList;
    }

    public String toString(){
        return name;
    }

}
