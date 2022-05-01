package game.actions;

import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.actors.Koopa;
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

		//Getting the attackers weapon
		Weapon weapon = actor.getWeapon();

		//If the attacker misses the target return string misses
		if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
			return actor + " misses " + target + ".";
		}

		//Getting the weapon damage
		int damage = weapon.damage();

		//Default string for any normal attacks
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";

		//If the attacker has consumed a power star, instant kill (max damage)
		if(actor.hasCapability(Status.INVINCIBLE)){
			damage = Integer.MAX_VALUE;
			result = actor + " " + weapon.verb() + " " + target + " and instantly kills it as " + actor + " is INVINCIBLE!";
		}

		//If the target has consumed a power star, nullifies all attacks. (0 damage)
		if(target.hasCapability(Status.INVINCIBLE)){
			damage = -1;
			return actor + " " + weapon.verb() + " " + target + " but " + target + "is INVINCIBLE!";
		}

		//If the target has consumed a mushroom, on hit removes the TALL capability (mushroom buff removed)
		if(target.hasCapability(Status.TALL)){
			//If the target has not consumed a power star
			if(damage != -1){
				target.removeCapability(Status.TALL);
				return actor + " " + weapon.verb() + " " + target + ", " + target + "is not TALL anymore.";
			}
		}

		//Hurting the target based on the damage value set from conditions above.
		target.hurt(damage);

		//If the target of attack is dead after the attack (<= 0 HP)
		if (!target.isConscious()) {

			//If the target is Koopa, just make it dormant
			if(target.getDisplayChar() == 'k') {
				target.hasCapability(Status.DORMANT);
				result += System.lineSeparator() + target + " is Dormant.";
				return result;
			}
			else {
				ActionList dropActions = new ActionList();
				for (Item item : target.getInventory())
					dropActions.add(item.getDropAction(actor));
				for (Action drop : dropActions)
					drop.execute(target, map);
				map.removeActor(target);
				result += System.lineSeparator() + target + " is killed.";
			}
		}
		return result;
	}


	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target + " at " + direction;
	}
}
