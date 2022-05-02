
package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.*;

/**
 * PurchaseAction class is a subclass of the engine class Action
 * @version 1.1.2
 * @author sthi0011, lcha0068, esea0003
 */
public class PurchaseAction extends Action {
    /**
     * An instance variable of type Item
     */
    private final Item item;

    /**
     * Constructor for PurchaseAction class
     *
     * @param item a purchasableitem
     */
    public PurchaseAction(Item item) {
        this.item = item;
    }

    /**
     * a method that will create an instance of PurchasableManager to get the list of purchasableItems, and
     * used it to search for the inputted item object so that it can access to the price() which was only for
     * the item classes that implement the Purchasable interface. Then it perform the trading process, add item
     * into inventory and minus balance off the actor's wallet balance.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return String description for the purchase outcome.
     */
    public String execute(Actor actor, GameMap map) {
        String execute = "";
        WalletManager wallet = WalletManager.getInstance();
        for (Purchasable purchasableItem : PurchasableManager.getInstance().getPurchasableItemList()) {
            if (item.equals(purchasableItem)) {

                if (wallet.getBalance(actor) >= purchasableItem.price()) {
                    wallet.minusBalance(actor, purchasableItem.price());
                    actor.addItemToInventory(item);
                    execute = item + " added to inventory.";
                } else {
                    execute = "Not enough coins";
                }
            }

        }
        return execute;
    }

    /**
     * Describe the action in a format suitable for displaying in the menu.
     * Uses a for loop to get the item price to display it in the menu
     * @param actor The actor performing the action.
     * @return Menu string for player to select the option to buy the inputted item
     */
    public String menuDescription(Actor actor) {
        int price= 0;

        for (Purchasable purchasableItem : PurchasableManager.getInstance().getPurchasableItemList()) {
            if (item.equals(purchasableItem)) {
                price = purchasableItem.price();
            }

        }
        return actor + " buys " + item + " ($" + price + ")";

    }
}


