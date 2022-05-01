package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Monologues;
import game.actors.Status;
import game.items.Wrench;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class SpeakAction extends Action {
    private Monologues monologues;

    @Override
    public String execute(Actor actor, GameMap map) {
        monologues = new Monologues();
        ArrayList<String> monologueList = monologues.getAllMonologues();
        for (Item item : actor.getInventory()) {
            if (item.getClass() == Wrench.class) {
                for (int i = 0; i < monologueList.size(); i++){
                    if(monologueList.get(i).equals("You might need a wrench to smash Koopa's hard shells.")){
                        monologueList.remove(i);
                    }

                }
            }
        }

        if (actor.hasCapability(Status.CONSUMER_STAR)) {
            for (int i = 0; i < monologueList.size(); i++){
                if(monologueList.get(i).equals("You better get back to finding the Power Stars.")){
                    monologueList.remove(i);
                }
            }
        }

        if (actor.hasCapability(Status.INVINCIBLE)) {
            for (int i = 0; i < monologueList.size(); i++){
                if(monologueList.get(i).equals("You better get back to finding the Power Stars.")){
                    monologueList.remove(i);
                }
            }
        }

        int randomNum = ThreadLocalRandom.current().nextInt(0, monologueList.size());
        String item = monologueList.get(randomNum);
        return item;
    }

    @Override
    public String menuDescription(Actor actor) {
        // changing later
        return actor + " speaks to Toad. ";
    }
}

