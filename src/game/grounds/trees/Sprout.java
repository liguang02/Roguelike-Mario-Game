package game.grounds.trees;

import edu.monash.fit2099.engine.positions.Location;
import game.utilities.Probability;
import game.actors.enemies.Goomba;
/**
 * @version 1.1.3
 * @author sthi0011, lcha0068, esea0003
 * This Sprout class is a subclass of Tree, which has its own spawning ability (spawns enemy goomba) and it grows into Sapling Tree
 */
public class Sprout extends Tree{
    /**
     * A tick counter to track the number of turns the game is in
     */
    private int tickCounter;

    /**
     * Constructor for Sprout class
     */
    public Sprout(){
        super('+', 90,10, "Sprout");
        tickCounter = 0;
    }


    /**
     * This method will be called in every turn, it handles all the necessary operations for this class,
     * such as spawning a goomba on its locatino at a specific chance and grows into Sapling every 10 turns.
     * @param location The location of the Sprout object
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        int goombaSpawnChance = 10;
        if (Probability.success(goombaSpawnChance) && !location.containsAnActor()) {
            location.addActor(new Goomba());
        }
        tickCounter++;
        if (tickCounter % this.getTreeAge() == 0) {
            location.setGround(new Sapling());
        }
    }

}
