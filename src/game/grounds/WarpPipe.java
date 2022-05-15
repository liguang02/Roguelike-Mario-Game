package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;

public class WarpPipe extends HighGround {
    /**
     * Name of the high ground, Warp pipe
     */
    private final String name;

    /**
     * Constructor for WarpPipe class
     */
    public WarpPipe() {
        super('C', 100, 0);
        this.name = "Warp Pipe";
    }
    public String toString(){
        return name;
    }

}
