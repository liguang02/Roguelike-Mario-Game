package game.grounds;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.GroundInflictAction;
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
     * A method to check if the actor is able to enter the ground or not
     * @param actor the Actor to check
     * @return a boolean value that returns False if the actor has the ENEMY status
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return !actor.hasCapability(Status.ENEMY);
    }

    /**
     * A toString method that will return its name (lava)
     * @return name of this instance
     */
    public String toString(){
        return name;
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actionList = new ActionList();
        if (!actor.hasCapability(Status.ENEMY)){
            actionList.add(new GroundInflictAction(Status.FIRE, damage));
        }
        return actionList;
    }
}
