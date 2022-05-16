package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import game.items.permanent.Bottle;

import java.util.HashMap;
import java.util.Map;

public class BottleManager {
    /**
     * An hashmap of actors(key) that has wallet and its wallet value(value)
     */
    private final Map<Actor, Bottle> bottleActorHashMap;

    /**
     * A walletManager instance
     */
    private static BottleManager instance;

    /**
     * A constructor for WalletManager
     */
    private BottleManager(){
        bottleActorHashMap = new HashMap<>();
    }

    /**
     * A getter that will return an instance of WalletManager
     * @return An instance of type WalletManager
     */
    public static BottleManager getInstance(){
        if (instance == null){
            instance = new BottleManager();
        }
        return instance;
    }
    /**
     * A method that will add the actor and wallet value into the hashmap
     * @param actor the actor that has wallet
     */
    public void addBottle(Actor actor, Bottle bottle){
        bottleActorHashMap.put(actor,bottle);
    }

    /**
     * A method that will return the wallet balance of the input actor
     * @param actor the actor that we will be its wallet balance
     * @return wallet balance of the actor
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
