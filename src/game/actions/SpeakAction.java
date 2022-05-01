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

    @Override
    public String execute(Actor actor, GameMap map) {
        Monologues monologues = new Monologues();
        ArrayList<String> monologueList = monologues.getAllMonologues();
        for (Item item : actor.getInventory()) {
            //Added WRENCH capability to avoid the getClass() we lose marks for that - Shantanu
            if (item.hasCapability(Status.WRENCH)) {
                for (int i = 0; i < monologueList.size(); i++){
                    if(monologueList.get(i).equals("You might need a wrench to smash Koopa's hard shells.")){
                        monologueList.remove(i);
                    }

                }
            }
        }

        //Removed the unnecessary if check of consume star enum - Shantanu

        if (actor.hasCapability(Status.INVINCIBLE)) {
            for (int i = 0; i < monologueList.size(); i++){
                if(monologueList.get(i).equals("You better get back to finding the Power Stars.")){
                    monologueList.remove(i);
                }
            }
        }

        int randomNum = ThreadLocalRandom.current().nextInt(0, monologueList.size());
        return monologueList.get(randomNum);
    }

    @Override
    public String menuDescription(Actor actor) {
        // changing later
        return actor + " speaks to Toad. ";
    }
}

