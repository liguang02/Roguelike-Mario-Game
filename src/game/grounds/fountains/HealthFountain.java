package game.grounds.fountains;

import game.items.consumable.HealingWater;

/**
 * @version 1.0.0
 * @author sthi0011
 * The Health Fountain ground
 */
public class HealthFountain extends Fountain {
    /**
     * Constructor for the HealthFountain, sets it to have HealingWater water in it.
     */
    public HealthFountain() {
        super('H', "Health Fountain");
        this.fillFountain(new HealingWater());
    }
}
