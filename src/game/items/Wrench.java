package game.items;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actors.Status;

public class Wrench extends WeaponItem implements Purchasable{

    private final int price;

    public Wrench() {
        super("Wrench", 'w', 50, "hits", 80);
        this.price = 200;
        this.addToPurchasableManager();
        this.addCapability(Status.WRENCH);
    }
    public int price(){
        return price;
    }



}
