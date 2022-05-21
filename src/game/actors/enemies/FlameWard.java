package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;
import game.behaviours.GroundDestroyBehaviour;
import game.grounds.Fire;
import game.items.consumable.FirePotion;
import game.utilities.Status;

/**
 * The Flame Ward enemy
 * @version 1.0.0
 * @author sthi0011
 */
public class FlameWard extends Enemy{

    /**
     * Constructor for the flame ward.
     */
    public FlameWard() {
        super("Flame Ward", 'Ö', 500);
        getBehaviours().put(2, new GroundDestroyBehaviour(new Fire()));
        this.addItemToInventory(new FirePotion());
    }

    /**
     * Either spits fire or does nothing. (Broken ward does nothing)
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return Action to take by flame ward.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        Action deathAction = onDeath();

        if(deathAction != null){
            return deathAction;
        }

        if(this.hasCapability(Status.BROKEN)){
            return new DoNothingAction();
        }

        for(game.behaviours.Behaviour Behaviour : getBehaviours().values()) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     * Allowing the actor to attack the flame ward.
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return Actions to take on flame ward.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }

    /**
     * If the enemy is killed normally.
     * Ward is broken on 1st death not directly killed.
     * Clean up reset manager, don't want it to do useless actions (does not crash game even without this)
     */
    public Action onDeath() {

        if(this.hasCapability(Status.BROKEN) && this.hasCapability(Status.DEAD)){
            return super.onDeath();
        }

        if (this.hasCapability(Status.DEAD)) {
            this.setDisplayChar('Ø');
            this.getBehaviours().clear();
            this.heal(Integer.MAX_VALUE);
            this.removeCapability(Status.DEAD);
            this.addCapability(Status.BROKEN);
        }
        return null;
    }
}
