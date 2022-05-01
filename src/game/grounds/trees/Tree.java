package game.grounds.trees;

import edu.monash.fit2099.engine.positions.Location;
import game.Probability;
import game.actors.Status;
import game.grounds.Dirt;
import game.grounds.HighGround;
import game.reset.ResetManager;
import game.reset.Resettable;

public abstract class Tree extends HighGround implements Resettable {
    /**
     *Name of the tree
     */
    private String name;



    /**
     *A fixed int value for tree to grow into the next stage
     */
    protected int treeAge = 10;

    /**
     * Constructor for Tree abstract class that extends from highground
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
     * Checks if a reset occured, REMOVED in capability set and if probability returns true (50% chance) remove the tree and make it dirt
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
    }


    public String toString(){
        return name;
    }

    public int getTreeAge() {
        return treeAge;
    }


}
