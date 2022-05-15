package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.MoveToTrapAction;
import game.utilities.Status;

public class Lava extends Ground {
    private String name;
    private int damage;
    /**
     * Constructor for Lava ground class
     *
     */
    public Lava() {
        super('L');
        this.addCapability(Status.FIRE);
        name =  "Lava";
        damage = 15;
    }

    /**
     * A method to check if the actor is able to enter the ground or not, this overriden canActorEnter() will return false as we will only perform the moving action
     * for the player(no enemies can walk through this ground) by calling MoveToTrapAction class.
     * @param actor the Actor to check
     * @return a boolean value that returns False if the actor has the ENEMY status
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    /**
     * A toString method that will return its name (lava)
     * @return name of this instance
     */
    public String toString(){
        return name;
    }

    @Override
    public void tick(Location location) {
        super.tick(location);
        Actor actor = location.getActor();
        if (actor != null && !actor.hasCapability(Status.ENEMY)){
            actor.hurt(damage);
            if(!actor.isConscious()){
                actor.addCapability(Status.DEAD);
            }
        }
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actionList = new ActionList();
        if (!actor.hasCapability(Status.ENEMY)&& location.getActor() != actor) {
            actionList.add(new MoveToTrapAction(damage, location, direction));
        }
        return actionList;
    }
}
