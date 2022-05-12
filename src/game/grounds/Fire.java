package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import game.utilities.Status;

public class Fire extends Ground {
    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public Fire(char displayChar) {
        super('₳');
        this.addCapability(Status.BURN);
    }
}
