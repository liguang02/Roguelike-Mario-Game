package game.actors.allies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.speech.Monologue;
import game.actions.PurchaseAction;
import game.actors.allies.Ally;
import game.items.consumable.PowerStar;
import game.items.consumable.SuperMushroom;
import game.items.permanent.Hammer;
import game.items.permanent.Wrench;
import game.actions.speech.SpeakAction;
import game.utilities.Status;

/**
 * Toad is a NPC that extends from the actor. Its sole purpose is to trade and speak with the player actor.
 * @version 1.1.2
 * @author sthi0011, lcha0068, esea0003
 */
public class Toad extends Ally {

    private final Monologue monologues = new Monologue();

    public Toad(){
        super("Toad", 'o',50);
        monologues.addMonologues(Status.WRENCH, "You might need a wrench to smash Koopa's hard shells.");
        monologues.addMonologues(Status.INVINCIBLE, "You better get back to finding the Power Stars.");
        monologues.addMonologues(Status.GENERIC, "The Princess is depending on you! You are our only hope.");
        monologues.addMonologues(Status.GENERIC, "Being imprisoned in these walls can drive a fungus crazy :(");
    }

    /**
     * Occurs at the start of every turn, this method will add a DoNothingAction for toad as it will never move or attack
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return a DoNothingAction
     */
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /**
     * This method will add all the necessary actions that toad are responsible of, such as PurchaseAction
     * and SpeakAction.
     * @param otherActor the Actor that might be performing speak or buy action
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return a list of actions, ActionList
     */
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        actions.add(new PurchaseAction(new Wrench()));
        actions.add(new PurchaseAction(new Hammer()));
        actions.add(new PurchaseAction(new SuperMushroom()));
        actions.add(new PurchaseAction(new PowerStar()));
        actions.add(new SpeakAction(monologues, this));
        return actions;
    }
}
