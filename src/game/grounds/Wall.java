package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;

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
