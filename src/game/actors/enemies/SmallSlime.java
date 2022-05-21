package game.actors.enemies;

import game.items.consumable.FirePotion;
import game.utilities.Probability;

/**
 * The SmallSlime enemy.
 * @version 1.1.2
 * @author sthi0011, lcha0068, esea0003
 */
public class SmallSlime extends SlimeTroop {

    /**
     * Constructor for the SmallSlime enemy that adds the capability status
     * with FIRE_IMMUNE (to make the enemy immune to lava and fire grounds)
     * It has a FirePotion which will be dropped once the player defeats the
     * enemy. This has a 50% chance of forming upon instantiation.
     * Also like other enemies, it will wander around the map.
     */
    public SmallSlime() {
        super("Small Slime", 's', 80);
        int chance = 5;
        if(Probability.success(chance)){
            this.addItemToInventory(new FirePotion());
        }
    }
}