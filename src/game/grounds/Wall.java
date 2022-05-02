package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;

/**
 * @version 1.0.1
 * @author sthi0011, lcha0068, esea0003
 * Wall class is one of the sub class of HighGround that the player can jump onto
 */
public class Wall extends HighGround{
	/**
	 * Name of the highground, wall
	 */
	private String name;

	/**
	 * Constructor for wall class
	 */
	public Wall() {
		super('#', 80, 20);
		this.name = "Wall";
	}

	/**
	 * Indicates that this ground class is able to blocks throwing objects
	 * @return True
	 */
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}

	/**
	 * A toString method that will return its name (wall)
	 * @return name of this instance
	 */
	public String toString(){
		return name;
	}


}
