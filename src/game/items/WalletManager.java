package game.items;

import edu.monash.fit2099.engine.actors.Actor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WalletManager {
    /**
     * An array list of actors that has wallet
     */
    private Map<Actor, Integer> walletActorHashMap;

    /**
     * A walletManager instance
     */
    private static WalletManager instance;

    //constructor

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
     * A method that append an item of type TaxableVehicle into the taxableVehicleList
     */
    public void appendWalletActor(Actor actor, int value){
        addBalance(actor, value);

    }

    /**
     * A getter for the taxableVehicleList
     * @return a new arraylist of taxableVehicle, so that it can prevent any modification made by outside this class.
     */
    public HashMap<Actor, Integer> getWalletActor() {
        return new HashMap<>(this.walletActorHashMap);
    }

    public void addBalance(Actor actor, int value){
        if (walletActorHashMap.isEmpty()){
            walletActorHashMap.put(actor, value);
        }
        else{
            walletActorHashMap.put(actor, walletActorHashMap.get(actor) +value);
        }
    }
    public int getBalance(Actor actor){
        if (walletActorHashMap.isEmpty()){
            return 0;
        }
        else{
            return walletActorHashMap.get(actor);
        }
    }
    public void minusBalance(Actor actor, int value){
        if (!walletActorHashMap.isEmpty()) {
            walletActorHashMap.put(actor, walletActorHashMap.get(actor) - value);
        }
    }
}
