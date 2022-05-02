package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Monologues;
import game.actors.Status;
import game.items.Wrench;

import java.util.HashMap;
import java.util.Random;

public class SpeakAction extends Action {
    private Monologues monologues;

    @Override
    public String execute(Actor actor, GameMap map) {
        monologues = new Monologues();
        HashMap<Integer, String> monologueList = monologues.getAllMonologues();
        if (actor.hasCapability(Status.WRENCH)){
                monologueList.remove(0);
        }

        if (actor.hasCapability(Status.INVINCIBLE)) {
            for (int i = 0; i < monologueList.size(); i++){
                monologueList.remove(1);
            }
        }

        Object randomNum = monologueList.keySet().toArray()[new Random().nextInt(monologueList.keySet().toArray().length)];
        String item = monologueList.get(randomNum);
        return item;
    }

    @Override
    public String menuDescription(Actor actor) {
        // changing later
        return actor + " speaks to Toad. ";
    }
}

