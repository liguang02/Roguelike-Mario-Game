package game.grounds.trees;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.Probability;
import game.actors.Koopa;
import game.actors.Status;
import game.items.Coin;

public class Mature extends Tree {
    private int tickCounter = 0;

    public Mature() {
        super('T',70,30, "Mature");
        tickCounter = 0;
    }
    @Override
    public void tick(Location location) {
        super.tick(location);
        int koopaSpawnChance = 15;
        if (Probability.success(koopaSpawnChance)){
            location.addActor(new Koopa());
        }
        tickCounter++;
        if (tickCounter %5 == 0){
            //get the exit object from the current location of the mature tree
            for(Exit exit : location.getExits()){
                // get the location of the surrounding exit
                Location surrounding = exit.getDestination();
                //get the ground of the location and check if it has the fertile status (so that we can grow new sprout)
                if (surrounding.getGround().hasCapability(Status.FERTILE)){
                    location.setGround(new Sprout());
                }
            }
        }
    }
}
