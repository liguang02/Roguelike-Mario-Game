package game.actions;

import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.grounds.Fire;
import game.utilities.Status;

/**
 * Special Action for attacking other Actors.
 * @version 1.1.2
 * @author sthi0011, lcha0068, esea0003
 */
public class AttackAction extends Action {
	/**
	 * The Actor that is to be attacked
	 */
	private final Actor target;

	/**
	 * The direction of incoming attack.
	 */
	private final String direction;

	/**
	 * Random number generator
	 */
	private final Random rand = new Random();

	/**
	 * Constructor for AttackAction class
	 * 
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
	}

	/**
	 * Executes attack between the actor and target based on their weapons, capabilities and HP.
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return String of the result from the attack.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {

		Weapon weapon = actor.getWeapon();
		if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
			return actor + " misses " + target + ".";
		}
		int damage = weapon.damage();
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";

		if(actor.hasCapability(Status.INVINCIBLE)){
			damage = Integer.MAX_VALUE;
			result = actor + " " + weapon.verb() + " " + target + " and instantly kills it as " + actor + " is INVINCIBLE!";
		}

		if(target.hasCapability(Status.INVINCIBLE)){
			return actor + " " + weapon.verb() + " " + target + " but " + target + " is INVINCIBLE!";
		}

		if(target.hasCapability(Status.TALL)){
				target.removeCapability(Status.TALL);
				return actor + " " + weapon.verb() + " " + target + ", " + target + " is not TALL anymore.";
		}

		if(actor.hasCapability(Status.BOWSER)){
			map.locationOf(target).setGround(new Fire());
			result =  actor + " " + weapon.verb() + " " + target + " dealing " + damage + " and dropped a fire on the ground where " + target + " is standing.";
		}

		target.hurt(damage);

		if (!target.isConscious()) {
			//If Koopa killed by normal player, shell activated.
			if(target.hasCapability(Status.SHELL) && !actor.hasCapability(Status.INVINCIBLE)) {
				target.addCapability(Status.DORMANT);
				result += System.lineSeparator() + target + " is Dormant.";
				return result;
			}
			//For all other situations, death = removed from map and items dropped
			else {
				target.addCapability(Status.DEAD);
			}
		}
		return result;
	}


	/**
	 * Returns the menu description of the attack action.
	 * @param actor The actor performing the action.
	 * @return String, actor attacking target in which direction.
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target + " at " + direction + " with " + actor.getWeapon();
	}
}
