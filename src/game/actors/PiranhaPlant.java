package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.AttackAction;
import game.behaviours.AttackBehaviour;
import game.utilities.Status;

public class PiranhaPlant extends Enemy {
    public PiranhaPlant(){
        super("Piranha Plant", 'P', 5);
    }

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

    @Override
    public void registerInstance() {
        super.registerInstance();
    }
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        // drop fire (not implemented yet)
        return new IntrinsicWeapon(5, "chomp");
    }
}
