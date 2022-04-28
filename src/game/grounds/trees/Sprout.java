package game.grounds.trees;

import edu.monash.fit2099.engine.positions.Location;
import game.Probability;
import game.actors.Goomba;

public class Sprout extends Tree{
    private int tickCounter = 0;

    public Sprout(){
        super('+', 90,10, "Sprout");
        tickCounter = 0;
    }



    @Override
    public void tick(Location location) {
        super.tick(location);
        int goombaSpawnChance = 10;
        if (Probability.success(goombaSpawnChance) && !location.containsAnActor()){
            location.addActor(new Goomba());
        }
        tickCounter++;
        if (tickCounter %10 == 0){
            location.setGround(new Sapling());
        }

    }

}
