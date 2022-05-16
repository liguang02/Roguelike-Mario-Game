package game.items;

import edu.monash.fit2099.engine.actors.Actor;

import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.1.2
 * @author sthi0011, lcha0068, esea0003
 * WalletManager class to manage the players funds
 */
public class WalletManager {
    /**
     * A hashmap of actors(key) that has wallet and its wallet value(value)
     */
    private final Map<Actor, Integer> walletActorHashMap;

    /**
     * A walletManager instance
     */
    private static WalletManager instance;


    /**
     * A constructor for WalletManager
     */
    private WalletManager(){
        walletActorHashMap = new HashMap<>();
    }

    /**
     * A getter that will return an instance of WalletManager
     * @return An instance of type WalletManager
     */
    public static WalletManager getInstance(){
        if (instance == null){
            instance = new WalletManager();
        }
        return instance;
    }

    /**
     * A method that will add the actor and wallet value into the hashmap
     * @param actor the actor that has wallet
     * @param value the value that is going to be added into the corresponding actor's wallet.
     */
    public void addBalance(Actor actor, int value){
        if (walletActorHashMap.isEmpty()){
            walletActorHashMap.put(actor, value);
        }
        else{
            walletActorHashMap.put(actor, walletActorHashMap.get(actor) +value);
        }
    }

    /**
     * A method that will return the wallet balance of the input actor
     * @param actor the actor that we will be its wallet balance
     * @return wallet balance of the actor
     */
    public int getBalance(Actor actor){
        if (walletActorHashMap.isEmpty()){
            return 0;
        }
        else{
            return walletActorHashMap.get(actor);
        }
    }

    /**
     * A method that will minus balance off from the actor's wallet balance. e.g (buying items)
     * @param actor the actor that we wish to minus its wallet balance
     * @param value the amount of value that is going to be deducted in the actor's wallet balance
     */
    public void minusBalance(Actor actor, int value){
        if (!walletActorHashMap.isEmpty()) {
            walletActorHashMap.put(actor, walletActorHashMap.get(actor) - value);
        }
    }
}
