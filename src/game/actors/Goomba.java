package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.utilities.Probability;
import game.actions.AttackAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.SuicideBehaviour;

/**
 * A little fungus guy.
 * @version 1.1.2
 * @author sthi0011, lcha0068, esea0003
 */
public class Goomba extends Enemy {

	/**
	 * A constructor to generate an instance of a Goomba which will have the Suicide behaviour
	 */
	public Goomba() {
		super("Goomba", 'g', 50);
		getBehaviours().put(2, new SuicideBehaviour());
	}

	/**
	 * When the Goomba is near another actor which has the enum status HOSTILE_TO_ENEMY
	 * then it will have certain behaviours.
	 *
	 * @param otherActor the Actor that might perform an action.
	 * @param direction  String representing the direction of the other Actor
	 * @param map        current GameMap
	 * @return list of actions
	 * @see Status#HOSTILE_TO_ENEMY
	 */
	@Override
	public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
		ActionList actions = new ActionList();
		// it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
		if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
			actions.add(new AttackAction(this,direction));
			getBehaviours().remove(1);
			getBehaviours().put(3, new AttackBehaviour(direction, otherActor));
			getBehaviours().put(4, new FollowBehaviour(otherActor));
		}
		return actions;
	}

	/**
	 * playturn is used after the player performs its action
	 * @see Actor#playTurn(ActionList, Action, GameMap, Display)
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

		Action deathAction = super.onDeath();
		if(deathAction != null){
			return deathAction;
		}

		if(Probability.success(10)){
			this.addCapability(Status.REMOVED);
			this.getBehaviours().clear();
			this.getBehaviours().put(1, new SuicideBehaviour());
		}

		for(Behaviour Behaviour : getBehaviours().values()) {
			Action action = Behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		return new DoNothingAction();
	}

	@Override
	/**
	 * Enemy's default Attack action which returns the damage and verb of the attack
	 */
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(10, "kick");
	}
}
