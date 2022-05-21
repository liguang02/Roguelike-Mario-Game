package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.AttackAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.utilities.Status;

/**
 * SlimeTroop abstract class, created for creative mode (REQ 4, Assignment 3).
 * @version 1.1.1
 * @author sthi0011, lcha0068, esea0003
 */
abstract class SlimeTroop extends Enemy {
    /**
     * The SlimeTroop is an abstract class that extends Enemy which is used by the Slime enemies.
     * Has FIRE_IMMUNE Status (to make the enemy immune to lava and fire grounds)
     * Enables the Enemy to wander around.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public SlimeTroop(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.FIRE_IMMUNE);
        getBehaviours().put(10, new WanderBehaviour());
    }
    /**
     * To check for any actions that can be performed by the SlimeTroops when it encounters another actor which
     * has HOSTILE_TO_ENEMY.
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return actions, a list of actions that can be performed by SlimeTroops
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
            getBehaviours().put(2, new AttackBehaviour(direction, otherActor));
            getBehaviours().put(3, new FollowBehaviour(otherActor));
        }
        return actions;
    }

    /**
     *  PlayTurn is used after the player performs its action
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
     * Register instance of the SlimeTroops to be reset if mario resets the game.
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
        return new IntrinsicWeapon(30, "slimes");
    }
}
