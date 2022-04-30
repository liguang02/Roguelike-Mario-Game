package game.items;

import java.util.ArrayList;

public class PurchasableManager {
    private ArrayList<Purchasable> purchasableItemList;

    private static PurchasableManager instance;

    private PurchasableManager(){
        purchasableItemList = new ArrayList<>();
    }

    public static PurchasableManager getInstance(){
        if (instance == null){
            instance = new PurchasableManager();
        }
        return instance;
    }

    public void appendPurchasableItem(Purchasable item){
        this.purchasableItemList.add(item);
    }

    public ArrayList<Purchasable> getPurchasableItemList(){
        return new ArrayList<Purchasable>(this.purchasableItemList);
    }
}
