package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import game.utilities.Status;

/**
 * @version 1.0.0
 * @author sthi0011, lcha0068, esea0003
 * A class that represents bare dirt.
 */
public class Dirt extends Ground {
	private String name;

	/**
	 * A constructor for dirt class
	 */
	public Dirt() {
		super('.');
		this.addCapability(Status.FERTILE);
		name = "Dirt";
	}

	@Override
	public String toString() {
		return name;
	}
}
