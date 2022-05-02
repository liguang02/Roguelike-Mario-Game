package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.actors.Status;

/**
 * A class that represents the floor inside a building.
 */
public class Floor extends Ground {
	/**
	 * A constructor for floor class
	 */
	public Floor() {
		super('_');
	}

	/**
	 * A method to check if the actor is able to enter the ground or not
	 * @param actor the Actor to check
	 * @return a boolean value that returns False if the actor has the ENEMY status
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return !actor.hasCapability(Status.ENEMY);
	}
}
