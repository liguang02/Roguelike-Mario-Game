package game.items;

public interface Purchasable {

    int price();

    default  void addToPurchasableManager(){
        PurchasableManager.getInstance().appendPurchasableItem(this);
    }
}
