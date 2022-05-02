package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import game.actors.Status;

/**
 * @version 1.0.0
 * @author sthi0011, lcha0068, esea0003
 * A class that represents bare dirt.
 */
public class Dirt extends Ground {

	/**
	 * A constructor for dirt class
	 */
	public Dirt() {
		super('.');
		this.addCapability(Status.FERTILE);
	}
}
