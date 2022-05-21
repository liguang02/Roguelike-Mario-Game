package game.items.permanent;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.items.Purchasable;

public class Hammer extends WeaponItem implements Purchasable {
    /**
     * An instance variable that stores the price value for Hammer
     */
    private final int price;

    /**
     * Constructor for Hammer class
     */
    public Hammer(){
        super("Hammer", 'H', 125, "bonks",75);
        price = 500;
        this.addToPurchasableManager();
    }
    /**
     * price method (a getter method to get the price of this item object)
     * Used in PurchaseAction where it needs to get the prices of the purchasableItem
     * @return an integer value representing the price of the item
     */
    public int price(){
        return price;
    }
}
