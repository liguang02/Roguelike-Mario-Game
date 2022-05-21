package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.BigSlime;
import game.utilities.Probability;
import game.utilities.Status;

public class Lava extends Ground {
    private final String name;
    private final int damage;
    private int tickCounter;
    /**
     * Constructor for Lava ground class
     *
     */
    public Lava() {
        super('L');
        this.addCapability(Status.LAVA);
        name =  "Lava";
        damage = 15;
        tickCounter = 0;
    }

    /**
     * A method to check if the actor is able to enter the ground or not, this overrides canActorEnter() will return false as we will only perform the moving action
     * for the player(no enemies can walk through this ground) by calling MoveToTrapAction class.
     * @param actor the Actor to check
     * @return a boolean value that returns False if the actor has the ENEMY status
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return (actor.hasCapability(Status.FIRE_IMMUNE));
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
        tickCounter++;
        int slimeSpawnChance = 5;
        if (!location.containsAnActor() && Probability.success(slimeSpawnChance) && (tickCounter%5)==0){
            location.addActor(new BigSlime());
        }
        Actor actor = location.getActor();
        if (actor != null && !actor.hasCapability(Status.ENEMY) && !actor.hasCapability(Status.FIRE_IMMUNE) && !actor.hasCapability(Status.INVINCIBLE)){
            actor.hurt(damage);
            if(!actor.isConscious()){
                actor.addCapability(Status.DEAD);
            }
        }if (actor != null && actor.hasCapability(Status.FIRE_IMMUNE)){
            actor.removeCapability(Status.FIRE_IMMUNE);
        }

    }


}
