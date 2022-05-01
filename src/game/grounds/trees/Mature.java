package game.grounds.trees;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.Probability;
import game.actors.Koopa;
import game.actors.Status;
import game.grounds.Dirt;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Mature extends Tree {
    private int tickCounter = 0;
//    private String name;

    public Mature() {
        super('T',70,30, "Mature");
        tickCounter = 0;
//        this.name = "Mature";
    }
    @Override
    public void tick(Location location) {
        super.tick(location);
        int koopaSpawnChance = 15;
        int witherChance = 20;
        if (Probability.success(koopaSpawnChance) && !location.containsAnActor()){
            location.addActor(new Koopa());
        }
        tickCounter++;
        if (tickCounter %5 == 0){
            ArrayList<Location> fertileGrounds = new ArrayList<Location>();
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
//    public String toString(){
//        return name;
//    }

}
