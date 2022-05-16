package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.AttackAction;
import game.actions.DormantAttackAction;
import game.behaviours.*;
import game.items.consumable.SuperMushroom;
import game.items.permanent.Wrench;
import game.utilities.Status;

/**
 * The Koopa enemy.
 * @version 1.1.2
 * @author sthi0011, lcha0068, esea0003
 */
public class Koopa extends KoopaTroop{

    /**
     * A constructor to generate an instance of a Koopa which has a SuperMushroom in its inventory
     * to be dropped when the Koopa is destroyed.
     */
    public Koopa() {

        super("Koopa", 'k', 50);
        this.addItemToInventory(new SuperMushroom());
        getBehaviours().put(10, new WanderBehaviour());
    }

    /**
     * To check for any actions that can be performed by the Koopa when it encounters another actor which
     * has HOSTILE_TO_ENEMY.
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY) && !this.hasCapability(Status.DORMANT)) {
            actions.add(new AttackAction(this, direction));
            getBehaviours().put(2, new AttackBehaviour(direction, otherActor));
            getBehaviours().put(3, new FollowBehaviour(otherActor));
        }

        //If DORMANT, only if player has a wrench can he attack the Koopa
        else{
            if(otherActor.getWeapon().toString().equals(new Wrench().toString()))
            actions.add(new DormantAttackAction(this, direction));
        }
        return actions;
    }

    /**
     * playturn is used after the player performs its action
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        Action deathAction = super.onDeath();
        if(deathAction != null){
            return deathAction;
        }

        if(this.hasCapability(Status.DORMANT)) {
            this.setDisplayChar('D');
            getBehaviours().clear();
            getBehaviours().put(1, new SuicideBehaviour());
        }

        for(Behaviour Behaviour : getBehaviours().values()) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
    return new DoNothingAction();
}

    /**
     * Enemy's default Attack action which returns the damage and verb of the attack
     * @return
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(30, "punch");
    }
}
