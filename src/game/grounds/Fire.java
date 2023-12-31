package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.utilities.Status;

/**
 *
 * @version 1.0.0
 * @author sthi0011, lcha0068, esea0003
 * A class that represents Fire ground
 */
public class Fire extends Ground {
    /**
     * Stores the name of the Fire Class
     */
    private final String name;
    /**
     * Stores the damage of the Fire Class
     */
    private final int damage;
    /**
     *Stores the tickCounter of the Fire Class
     */
    private int tickCounter;

    /**
     * Constructor for Fire Class.
     */
    public Fire() {
        super('v');
        this.addCapability(Status.FIRE);
        name = "Fire";
        damage = 20;
        tickCounter = 0;
    }

    /**
     * To check if the actor can enter the ground
     * @param actor the Actor to check
     * @return boolean of if actor can enter
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return true;
    }

    /**
     * To return the name of the ground as a string
     * @return a String which represents the name of the Ground
     */
    public String toString(){
        return name;
    }

    /**
     * Tracks the number of turns that has occurred.
     * The Fire Ground is replaced with Dirt Ground after 3 turns.
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        Actor actor = location.getActor();
        if (actor != null && !actor.hasCapability(Status.FIRE_IMMUNE) && !actor.hasCapability(Status.INVINCIBLE)){
            actor.hurt(damage);
            if(!actor.isConscious()){
                actor.addCapability(Status.DEAD);
            }
        }if (actor != null && actor.hasCapability(Status.FIRE_IMMUNE)){
            actor.removeCapability(Status.FIRE_IMMUNE);
        }
        if (tickCounter >= 3){
            location.setGround(new Dirt());
        }
        tickCounter++;
    }
}
