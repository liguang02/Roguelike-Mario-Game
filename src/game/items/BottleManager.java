package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import game.items.permanent.Bottle;
import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.1.2
 * @author sthi0011
 * BottleManager class to store and keep track of the bottles in actors inventory.
 */
public class BottleManager {
    /**
     * Hashmap to store the bottles with keys being the actor who has that bottle.
     */
    private final Map<Actor, Bottle> bottleActorHashMap;

    /**
     * A BottleManager instance
     */
    private static BottleManager instance;

    /**
     * A Constructor for BottleManager
     */
    private BottleManager(){
        bottleActorHashMap = new HashMap<>();
    }

    /**
     * A getter that will return an instance of BottleManager
     * @return An instance of type BottleManager
     */
    public static BottleManager getInstance(){
        if (instance == null){
            instance = new BottleManager();
        }
        return instance;
    }
    /**
     * A method that will add the actor and bottle to the hashmap
     * @param actor the actor that has the bottle
     */
    public void addBottle(Actor actor, Bottle bottle){
        bottleActorHashMap.put(actor,bottle);
    }

    /**
     * A method that will return the bottle of the input actor
     * @param actor the actor whose bottle we want to access
     * @return bottle of the actor (the bottle that is in the actors inventory)
     */
    public Bottle getBottle(Actor actor){
        if (bottleActorHashMap.isEmpty() || !bottleActorHashMap.containsKey(actor)){
            return null;
        }
        else{
            return bottleActorHashMap.get(actor);
        }
    }
}
