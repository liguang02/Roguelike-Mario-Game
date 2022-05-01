package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.actors.Status;

/**
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
