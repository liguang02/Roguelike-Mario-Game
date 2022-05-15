package game.behaviours;

import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.utilities.Status;

/**
 * WanderBehaviour is used  by the enemies to wander around the map.
 * @version 1.1.2
 * @author sthi0011, lcha0068, esea0003
 */
public class WanderBehaviour extends Action implements Behaviour {
	
	private final Random random = new Random();

	/**
	 * Returns a MoveAction to wander to a random location, if possible.  
	 * If no movement is possible, returns null.
	 * 
	 * @param actor the Actor enacting the behaviours
	 * @param map the map that actor is currently on
	 * @return an Action, or null if no MoveAction is possible
	 *
	 * Asgn3: Added FLYING- if the actor is able to fly, it will wander above any item
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		ArrayList<Action> actions = new ArrayList<>();
		
		for (Exit exit : map.locationOf(actor).getExits()) {
			Location destination = exit.getDestination();
			if (!(actor.hasCapability(Status.FLYING))) {
				if (destination.canActorEnter(actor)) {
					actions.add(exit.getDestination().getMoveAction(actor, "around", exit.getHotKey()));
				}
			} else {
				actions.add(exit.getDestination().getMoveAction(actor, "around", exit.getHotKey()));
			}
		}
		
		if (!actions.isEmpty()) {
			return actions.get(random.nextInt(actions.size()));
		}
		else {
			return null;
		}

	}

	/**
	 * Displays the menuDescription
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a String containing the menu description
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		return menuDescription(actor);
	}

	/**
	 * Prints display of the actor
	 * @param actor The actor performing the action.
	 * @return a string that represents the enemies noises
	 */
	@Override
	public String menuDescription(Actor actor) {
		return "Raagrh...";
	}
}
