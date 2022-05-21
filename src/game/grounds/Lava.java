package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.BigSlime;
import game.utilities.Probability;
import game.utilities.Status;

/**
 * This class represents lava ground that will deal damage to player if it is standing on it
 */
public class Lava extends Ground {
    /**
     * Name of the ground
     */
    private final String name;
    /**
     * the amount of damage it will deal to the player
     */
    private final int damage;
    /**
     * A tick counter to track the number of turns the game is in
     */
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
     * A method to check if the actor is able to enter the ground or not, this override canActorEnter() will check if the actor has the
     * capability FIRE IMMUNE
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

    /**
     * Every 5 turns of the game, every lava ground instance will have a 5% chance to spawn a big slime enemy.
     * Besides, if there is an actor stepping on the lava ground without FIRE IMMUNE capability, they will take some
     * amount of damage. This method will also remove the FIRE IMMUNE capability after the actor stepping on this ground if they have one.
     * @param location The location of the Ground
     */
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
