package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.AttackAction;
import game.actions.DormantAttackAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.SuicideBehaviour;
import game.items.SuperMushroom;
import game.items.Wrench;

public class Koopa extends Enemy{

    public Koopa() {
        super("Koopa", 'k', 50);
        this.addCapability(Status.SHELL);
        this.addItemToInventory(new SuperMushroom());
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY) && !this.hasCapability(Status.DORMANT)) {
            actions.add(new AttackAction(this, direction));
            getBehaviours().remove(1);
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

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(30, "punch");
    }
}
