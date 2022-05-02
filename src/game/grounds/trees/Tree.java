package game.grounds.trees;

import edu.monash.fit2099.engine.positions.Location;
import game.utilities.Probability;
import game.utilities.Status;
import game.grounds.Dirt;
import game.grounds.HighGround;
import game.reset.ResetManager;
import game.reset.Resettable;

/**
 * @version 1.1.2
 * @author sthi0011, lcha0068, esea0003
 * Tree is an abstract class that extends Highground abstract class.
 */
public abstract class Tree extends HighGround implements Resettable {
    /**
     *Name of the tree
     */
    private final String name;

    /**
     *A fixed int value for tree to grow into the next stage
     */
    protected int treeAge = 10;

    /**
     * Constructor for Tree abstract class that extends from high ground
     * @param displayChar the character that will be displayed on the map
     * @param successRate success rate of the actor jumping to a tree instance
     * @param damage the amount of fall damage the actor will take if it fails the jump
     * @param name name of the tree (Sprout, Sapling, Mature)
     */
    public Tree(char displayChar, int successRate, int damage, String name) {
        super(displayChar, successRate, damage);
        this.registerInstance();
        this.name = name;
    }


    /**
     * resetInstance just adds REMOVED capability to the capability set of the tree
     */
    @Override
    public void resetInstance() {
        this.addCapability(Status.REMOVED);
    }

    /**
     * If the tree has been removed before (Power Star buffed actor moves and destroys it), clears this instance from ResetManager
     * Checks if a reset occurred, REMOVED in capability set and if probability returns true (50% chance) remove the tree and make it dirt
     * @param location the location of the tree
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        if(location.getActor() != null && location.getActor().hasCapability(Status.INVINCIBLE)){
            ResetManager.getInstance().cleanUp(this);
        }
        if(this.hasCapability(Status.REMOVED) && Probability.success(50)){
            location.setGround(new Dirt());
        }
        else{
            this.removeCapability(Status.REMOVED);
        }
    }

    /**
     * A method that return the name of the tree. E.g. Mature, Sprout, Sapling
     * @return a string representing the name of the tree object
     */
    public String toString(){
        return name;
    }

    /**
     * a getter method for treeAge
     * @return an integer representing the age of the tree that grows into the next stage
     */
    public int getTreeAge() {
        return treeAge;
    }


}
