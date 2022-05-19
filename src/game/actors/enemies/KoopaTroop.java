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
 * The KoopaTroop is an abstract class that extends Enemy which is used by the Koopas.
 * @version 1.1.2
 * @author sthi0011, lcha0068, esea0003
 */
abstract class KoopaTroop extends Enemy {
    /**
     * Constructor for the generated Koopas. The Koopas have the capability of Shell in common.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public KoopaTroop(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.SHELL);
        this.addItemToInventory(new SuperMushroom());
        getBehaviours().put(10, new WanderBehaviour());
    }

    /**
     * To check for any actions that can be performed by the Koopa when it encounters another actor which
     * has HOSTILE_TO_ENEMY.
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return an ActionList of actions that can be performed by Koopa
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
     * PlayTurn is used after the player performs its action
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

        if(this.hasCapability(Status.DORMANT)) {
            this.setDisplayChar('D');
            getBehaviours().clear();
            getBehaviours().put(1, new SuicideBehaviour());
        }

        for(game.behaviours.Behaviour Behaviour : getBehaviours().values()) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     * Enemy's default Attack action which returns the damage and verb of the attack
     * @return default attack mode
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(30, "punch");
    }
}
