package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Player;
import game.items.*;

public class PurchaseAction extends Action {

    Item item;

    public PurchaseAction(Item item){
        this.item = item;
    }

    public String execute(Actor actor, GameMap map) {
        String execute = "";
        WalletManager wallet = WalletManager.getInstance();
        for (Purchasable purchasableItem : PurchasableManager.getInstance().getPurchasableItemList()){
            if (item.equals(purchasableItem)){
                if (wallet.getBalance(actor) >= purchasableItem.price()) {
                    wallet.minusBalance(actor, purchasableItem.price());
                    actor.addItemToInventory(item);
                    execute = item.toString() + " added to inventory.";
                } else {
                    execute = "Not enough coins";
                }
            }

        }
        return execute;
    }

    public String menuDescription(Actor actor) {
        return "Buy " + this.item + " from Toad.";
    }

}
