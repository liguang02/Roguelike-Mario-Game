package game.items;

import edu.monash.fit2099.engine.weapons.WeaponItem;

public class Wrench extends WeaponItem{

    /**
     * Constructor.
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate     the probability/chance to hit the target.
     */
    public Wrench(String name, char displayChar, int damage, String verb, int hitRate) {
        super("Wrench", 'w', 50, "hits", 80);
    }
}
