package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.PurchaseAction;
import game.items.PowerStar;
import game.items.SuperMushroom;
import game.items.Wrench;

public class Toad extends Actor {
    public Toad(){
        super("Toad", 'o',50);
    }
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        actions.add(new PurchaseAction(new Wrench()));
        actions.add(new PurchaseAction(new SuperMushroom()));
        actions.add(new PurchaseAction(new PowerStar()));
        return actions;
    }
}