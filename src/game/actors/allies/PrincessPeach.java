package game.actors.allies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.UnlockHandcuffsAction;
import game.actions.speech.Monologue;
import game.actions.speech.SpeakAction;
import game.actors.allies.Ally;
import game.utilities.Status;

/**
 * Princess Peach, an NPC which will only speak when the handcuffs are unlocked.
 * @version 1.1.2
 * @author esea0003
 */
public class PrincessPeach extends Ally {

    /**
     * constructor for Princess Peach.
     * monologues are immediately instantiated because Princess Peach only has one line.
     */
    public PrincessPeach() {
        super("Princess Peach", 'P',50);
    }

    /**
     * Occurs when player interacts with princess peach
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /**
     * unlock handcuffs action only appears when the actor has a key.
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return
     */
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.KEY)){
            actions.add(new UnlockHandcuffsAction());
        }
        return actions;
    }
}
