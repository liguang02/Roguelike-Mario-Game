package game.actions;

import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.actors.Status;

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {
	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;

	/**
	 * The direction of incoming attack.
	 */
	protected String direction;

	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

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
			return actor + " " + weapon.verb() + " " + target + " but " + target + "is INVINCIBLE!";
		}

		if(target.hasCapability(Status.TALL)){
				target.removeCapability(Status.TALL);
				return actor + " " + weapon.verb() + " " + target + ", " + target + "is not TALL anymore.";
		}

		target.hurt(damage);

		if (!target.isConscious()) {
			if(target.hasCapability(Status.SHELL)) {
				target.addCapability(Status.DORMANT);
				result += System.lineSeparator() + target + " is Dormant.";
				return result;
			}
			else {
				target.addCapability(Status.REMOVED);
				result += System.lineSeparator() + target + " is killed.";
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
		return actor + " attacks " + target + " at " + direction;
	}
}
