package game.actors.allies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.speech.Monologue;
import game.actions.speech.SpeakAction;
import game.actors.allies.Ally;
import game.utilities.Status;

public class PrincessPeach extends Ally {
    /**
     * constructor
     */
    private final Monologue monologues = new Monologue();
    public PrincessPeach() {
        super("Princess Peach", 'P',50);
        monologues.addMonologues(Status.KEY, "Thank you for saving me!");
        monologues.addMonologues(Status.GENERIC, "Dear Mario, I'll be waiting for you...");
        monologues.addMonologues(Status.GENERIC, "Never gonna give you up!");
        monologues.addMonologues(Status.GENERIC, "Release me, or I will kick you!");
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        actions.add(new SpeakAction(monologues, this));
        return actions;
    }
}
