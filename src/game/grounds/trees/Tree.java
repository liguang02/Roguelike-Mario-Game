package game.grounds.trees;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Probability;
import game.actors.Status;
import game.grounds.Dirt;
import game.grounds.HighGround;
import game.reset.Resettable;

public abstract class Tree extends HighGround implements Resettable {
    private String name;
    protected int age = 10;

    /**
     * Constructor.
     *
     */
    private final int growthCount = 10;


    public Tree(char displayChar, int successRate, int damage, String name) {
        super(displayChar, successRate, damage);
        this.registerInstance();
        this.name = name;
    }


    @Override
    public void resetInstance() {
        this.addCapability(Status.REMOVED);
    }

    @Override
    public void tick(Location location) {
        super.tick(location);
            if(this.hasCapability(Status.REMOVED) && Probability.success(50)){
                location.setGround(new Dirt());
            }
        this.removeCapability(Status.REMOVED);
    }
}
