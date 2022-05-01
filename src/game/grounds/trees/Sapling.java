package game.grounds.trees;

import edu.monash.fit2099.engine.positions.Location;
import game.Probability;
import game.actors.Goomba;
import game.actors.Status;
import game.items.Coin;

public class Sapling extends Tree{
    private int tickCounter;
//    private String name;

    public Sapling(){
        super('t', 80, 20, "Sapling");
        tickCounter = 0;
//        this.name = "Sapling"
    }

    @Override
    public void tick(Location location) {
        super.tick(location);
        int coinSpawnChance = 10;
        if (Probability.success(coinSpawnChance)){
            location.addItem(new Coin(20));
        }
        tickCounter++;
        if (tickCounter %10 == 0){
            location.setGround(new Mature());
        }

    }

}
