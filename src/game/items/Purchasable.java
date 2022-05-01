package game.items;

/**
 * Interface for items that are purchasable
 * It provides methods needed by the purchasable items, combining this interface class with
 * PurchasableManager, we can clearly know if an item is purchasable or not.
 */
public interface Purchasable {
    /**
     * The price value of the purchasable item
     * @return an integer representing the price/value of the item
     */
    int price();

    /**
     *Add the object that calls this method into the PurchasableItemList in the PurchasableManager class.
     */
    default  void addToPurchasableManager(){
        PurchasableManager.getInstance().appendPurchasableItem(this);
    }
}
