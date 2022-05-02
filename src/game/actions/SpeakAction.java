package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Monologue;
import game.actors.Status;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SpeakAction extends Action {

    private final Monologue monologues;
    private final Actor speaker;

    public SpeakAction(Monologue monologues, Actor speaker) {
        this.monologues = monologues;
        this.speaker = speaker;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if(speaker.toString().equals("Toad")){
            int randomNum = ThreadLocalRandom.current().nextInt(0, monologues.getToadMonologues(actor).size());
            return monologues.getToadMonologues(actor).get(randomNum);
        }

        int randomNum = ThreadLocalRandom.current().nextInt(0, monologues.getAllMonologues().size());
        return monologues.getAllMonologues().get(randomNum);
    }

    @Override
    public String menuDescription(Actor actor) {
        // changing later
        return actor + " speaks to Toad. ";
    }
}

