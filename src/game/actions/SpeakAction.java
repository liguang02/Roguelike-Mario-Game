package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Monologue;
import java.util.concurrent.ThreadLocalRandom;

/**
 * SpeakAction class for the actor to monologue
 */
public class SpeakAction extends Action {
    /**
     * The monologue class for the actor.
     */
    private final Monologue monologues;
    /**
     * The speaker who says the monologues
     */
    private final Actor speaker;

    /**
     * Constructor for SpeakaAction Class.
     * @param monologues the monologue class related to that speaker
     * @param speaker the actor for the speaker
     */
    public SpeakAction(Monologue monologues, Actor speaker) {
        this.monologues = monologues;
        this.speaker = speaker;
    }

    /**
     * Execute, showing the monologue.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return The monologue spoken.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if(speaker.toString().equals("Toad")){
            int randomNum = ThreadLocalRandom.current().nextInt(0, monologues.getToadMonologues(actor).size());
            return monologues.getToadMonologues(actor).get(randomNum);
        }

        int randomNum = ThreadLocalRandom.current().nextInt(0, monologues.getAllMonologues().size());
        return monologues.getAllMonologues().get(randomNum);
    }

    /**
     * menuDescription of actor speaking to the other actor.
     * @param actor The actor performing the action.
     * @return String of actor speaking to the speaker
     */
    @Override
    public String menuDescription(Actor actor) {
        // changing later
        return actor + " speaks to " + speaker + ".";
    }
}

