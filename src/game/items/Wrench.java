package game.items;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actors.Status;

/**
 * Wrench class is a subclass of WeaponItem which is a subclass of Item
 * @version 1.1.2
 * @author sthi0011, lcha0068, esea0003
 */
public class Wrench extends WeaponItem implements Purchasable{
    /**
     * An instance variable that stores the price value for Wrench
     */
    private final int price;

    /**
     * Constructor for Wrench class
     */
    public Wrench() {
        super("Wrench", 'w', 50, "hits", 80);
        this.price = 200;
        this.addToPurchasableManager();
        this.addCapability(Status.WRENCH);
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
