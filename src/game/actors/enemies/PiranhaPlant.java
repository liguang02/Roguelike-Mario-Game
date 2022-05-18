package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.AttackAction;
import game.actors.enemies.Enemy;
import game.behaviours.AttackBehaviour;
import game.utilities.Status;
/**
 * The PirahnaPlant enemy.
 * @version 1.1.2
 * @author sthi0011, lcha0068, esea0003
 */
public class PiranhaPlant extends Enemy {
    /**
     * Constructor for Piranha Plant.
     */
    public PiranhaPlant(){
        super("Piranha Plant", 'P', 150);
    }

    /**
     * To check for any actions that can be performed by the PiranhaPlant when it encounters another actor which
     * has HOSTILE_TO_ENEMY.
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return actions, a list of actions that can be performed by PiranhaPlant
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
            getBehaviours().remove(1);
            getBehaviours().put(2, new AttackBehaviour(direction, otherActor));
        }
        return actions;
    }

    /**
     * playturn is used after the player performs its action
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the action that occurs after the player performed the action.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        Action deathAction = super.onDeath();
        if(deathAction != null){
            return deathAction;
        }
        for(game.behaviours.Behaviour Behaviour : getBehaviours().values()) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     * Resets instance of the PiranhaPlant to be reset if mario resets the game
     * and increases the maxHP by 50.
     */
    @Override
    public void resetInstance() {
        this.increaseMaxHp(50);
        this.heal(getMaxHp());
    }

    /**
     * Register instance of the PiranhaPlant to be reset if mario resets the game.
     */
    @Override
    public void registerInstance() {
        super.registerInstance();
    }

    /**
     * Enemy's default Attack action which returns the damage and verb of the attack
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(90, "chomp");
    }
}
