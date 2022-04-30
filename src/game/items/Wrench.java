package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.weapons.WeaponItem;

public class Wrench extends WeaponItem implements Purchasable{
    private int price;

//    /**
//     * Constructor.
//     *
//     * @param name        name of the item
//     * @param displayChar character to use for display when item is on the ground
//     * @param damage      amount of damage this weapon does
//     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
//     * @param hitRate     the probability/chance to hit the target.
//     */
    public Wrench() {
        super("Wrench", 'w', 50, "hits", 80);
        this.price = 200;
        this.addToPurchasableManager();
    }
    public int price(){
        return price;
    }



}
