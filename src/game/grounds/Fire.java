package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.MoveToTrapAction;
import game.grounds.trees.Mature;
import game.utilities.Status;

public class Fire extends Ground {
    private String name;
    private int damage;
    private int tickCounter;
    /**
     * Constructor.
     *
     *
     */
    public Fire() {
        super('₳');
        this.addCapability(Status.FIRE);
        name = "Fire";
        damage = 20;
        tickCounter = 0;
    }

    //similar to lava class
    @Override
    public boolean canActorEnter(Actor actor) {
        return true;
    }

    public String toString(){
        return name;
    }

    @Override
    public void tick(Location location) {
        super.tick(location);
        Actor actor = location.getActor();
        if (actor != null){
            actor.hurt(damage);
            if(!actor.isConscious()){
                actor.addCapability(Status.DEAD);
            }
        }
        tickCounter++;
        if (tickCounter == 3){
            location.setGround(new Dirt());
        }
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actionList = new ActionList();
        if (location.getActor() != actor) {
            actionList.add(new MoveToTrapAction(damage, location, direction));
        }
        return actionList;
    }
}
