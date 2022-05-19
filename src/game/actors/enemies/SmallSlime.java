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
import game.items.consumable.FirePotion;
import game.items.permanent.Hammer;
import game.utilities.Probability;
import game.utilities.Status;
/**
 * The SmallSlime enemy.
 * @version 1.1.2
 * @author sthi0011, lcha0068, esea0003
 */
public class SmallSlime extends Enemy {

    /**
     * Constructor for the SmallSlime enemy that adds the capability status
     * with FIRE_IMMUNE (to make the enemy immune to lava and fire grounds)
     * It has a FirePotion which will be dropped once the player defeats the
     * enemy. This has a 50% chance of forming upon instantiation.
     * Also like other enemies, it will wander around the map.
     */
    public SmallSlime() {
        super("SmallSlime", 's', 80);
        this.addCapability(Status.FIRE_IMMUNE);
        int chance = 5;
        if(Probability.success(chance)){
            this.addItemToInventory(new FirePotion());
        }
        getBehaviours().put(10, new WanderBehaviour());
    }

    /**
     * To check for any actions that can be performed by the SmallSlime when it encounters another actor which
     * has HOSTILE_TO_ENEMY.
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return actions, a list of actions that can be performed by SmallSlime
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
     *  playturn is used after the player performs its action
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
     * Register instance of the SmallSlime to be reset if mario resets the game.
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
