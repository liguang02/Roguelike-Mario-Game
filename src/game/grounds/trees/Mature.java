package game.grounds.trees;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.FlyingKoopa;
import game.utilities.Probability;
import game.actors.enemies.Koopa;
import game.utilities.Status;
import game.grounds.Dirt;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
/**
 * @version 1.1.2
 * @author sthi0011, lcha0068, esea0003
 * This Mature class is a subclass of Tree, which has its own spawning ability (spawns enemy Koopa) and in every 5 turns,
 * it will spawns a sprout in a random fertile ground around it.
 */
public class Mature extends Tree {
    /**
     * A tick counter to track the number of turns the game is in
     */
    private int tickCounter;

    /**
     * Constructor for Mature class
     */
    public Mature() {
        super('T',70,30, "Mature");
        tickCounter = 0;
    }

    /**
     * This method will be called in every turn, it handles all the necessary operations for this class,
     * such as spawning koopas, wither at a specific chance and grows a sprout at a random surrounding fertile ground.
     * @param location The location of the Mature object
     *
     * Asgn3: 50% chance of spawning Koopa and 50% chance of spawning Flying Koopa.
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        int koopaSpawnChance = 15;
        int whichkoopaSpawnChance = 50;
        int witherChance = 20;
        if (Probability.success(koopaSpawnChance) && !location.containsAnActor()){
            if (Probability.success(whichkoopaSpawnChance)){
                location.addActor(new Koopa());
            } else {
                location.addActor(new FlyingKoopa());
            }
        }

        tickCounter++;
        if (tickCounter %5 == 0){
            ArrayList<Location> fertileGrounds = new ArrayList<>();
            //get the exit object from the current location of the mature tree
            for(Exit exit : location.getExits()){
                // get the location of the surrounding exit
                Location surrounding = exit.getDestination();
                //get the ground of the location and check if it has the fertile status
                if (surrounding.getGround().hasCapability(Status.FERTILE)){
                    // add the location of the fertile ground into the arraylist fertileGrounds
                    fertileGrounds.add(surrounding);
                }
            }
            // To randomly grow a sprout in the fertile ground
            int randomNum = ThreadLocalRandom.current().nextInt(0, fertileGrounds.size());
            Location randomFertile = fertileGrounds.get(randomNum);
            randomFertile.setGround(new Sprout());
        }
        // mature tree will have 20% of wither and become dirt every turn.
        if (Probability.success(witherChance)){
            location.setGround(new Dirt());
        }
    }


}
