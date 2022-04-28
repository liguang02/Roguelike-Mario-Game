package game.grounds.trees;

import edu.monash.fit2099.engine.positions.Ground;
import game.actors.Status;
import game.reset.Resettable;

public class Tree extends Ground implements Resettable {

    /**
     * Constructor.
     *
     */
    public Tree() {
        super('+');
        registerInstance();
    }


    @Override
    public void resetInstance() {
        this.addCapability(Status.REMOVED);
    }
}
