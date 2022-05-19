package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.utilities.Status;

/**
 * @version 1.0.0
 * @author sthi0011, lcha0068, esea0003
 * A class that represents the floor inside a building.
 */
public class Fire extends Ground {
    /**
     *
     */
    private final String name;
    /**
     *
     */
    private final int damage;
    /**
     *
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
     *
     * @param actor the Actor to check
     * @return
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return true;
    }

    public String toString(){
        return name;
    }

    /**
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        Actor actor = location.getActor();
        if (actor != null && !actor.hasCapability(Status.FIRE_IMMUNE)){
            actor.hurt(damage);
            if(!actor.isConscious()){
                actor.addCapability(Status.DEAD);
            }
        }if (actor != null && actor.hasCapability(Status.FIRE_IMMUNE)){
            actor.removeCapability(Status.FIRE_IMMUNE);
        }
        tickCounter++;
        if (tickCounter == 4){
            location.setGround(new Dirt());
        }
    }
}
