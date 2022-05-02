package game.grounds.trees;

import edu.monash.fit2099.engine.positions.Location;
import game.utilities.Probability;
import game.items.Coin;

/**
 * @version 1.1.2
 * @author sthi0011, lcha0068, esea0003
 * This Sapling class is a subclass of Tree, which has its own spawning ability (spawns coin) and it grows into Mature Tree
 */
public class Sapling extends Tree{
    /**
     * A tick counter to track the number of turns the game is in
     */
    private int tickCounter;

    /**
     * Constructor for Sapling class
     */
    public Sapling(){
        super('t', 80, 20, "Sapling");
        tickCounter = 0;
    }
    /**
     * This method will be called in every turn, it handles all the necessary operations for this class,
     * such as spawning a fixed value of coin at a specific chance and grows into mature every 10 turns.
     * @param location The location of the Sapling object
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        int coinSpawnChance = 10;
        if (Probability.success(coinSpawnChance)){
            location.addItem(new Coin(20));
        }
        tickCounter++;
        if (tickCounter % this.getTreeAge() == 0){
            location.setGround(new Mature());
        }

    }

}
