package game.items;

import java.util.ArrayList;

/**
 * PurchasableManager
 *  @author lcha0068
 *  @version 1.0.0
 */
public class PurchasableManager {
    /**
     * An array list of purchasable items
     */
    private ArrayList<Purchasable> purchasableItemList;

    /**
     * A PurchasableManager instance
     */
    private static PurchasableManager instance;

    /**
     * A constructor for PurchasableManager
     */
    private PurchasableManager(){
        purchasableItemList = new ArrayList<>();
    }

    /**
     * A getter that will return an instance of PurchasableManager
     * @return an instance of type PurchasableManager
     */
    public static PurchasableManager getInstance(){
        if (instance == null){
            instance = new PurchasableManager();
        }
        return instance;
    }

    /**
     * A method that append an item of type PurchasableItem into the purchasableItemList
     * @param item
     */
    public void appendPurchasableItem(Purchasable item){
        this.purchasableItemList.add(item);
    }

    /**
     * A getter for the purchasableItemList
     * @return a new arraylist of purchasableItem, so that it can prevent any modification made by outside this class
     */
    public ArrayList<Purchasable> getPurchasableItemList(){
        return new ArrayList<Purchasable>(this.purchasableItemList);
    }
}
