package game.items;

import edu.monash.fit2099.engine.actions.Action;

public interface Purchasable {
    int price();
    default  void addToPurchasableManager(){
        PurchasableManager.getInstance().appendPurchasableItem(this);
    }
}
