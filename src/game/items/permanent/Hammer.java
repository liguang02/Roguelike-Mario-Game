package game.items.permanent;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.items.Purchasable;
import game.utilities.Status;

public class Hammer extends WeaponItem implements Purchasable {
    private final int price;

    public Hammer(){
        super("Hammer", 'H', 125, "bonks",75);
        price = 500;
        this.addToPurchasableManager();
    }

    public int price(){
        return price;
    }
}
