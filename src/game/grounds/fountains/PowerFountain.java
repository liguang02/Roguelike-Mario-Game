package game.grounds.fountains;

import game.items.consumable.PowerWater;

/**
 * @version 1.0.0
 * @author sthi0011
 * The Power Fountain ground
 */
public class PowerFountain extends Fountain {
    /**
     * Constructor for the PowerFountain, sets it to have PowerWater water in it.
     */
    public PowerFountain() {
        super('A', "Power Fountain");
        this.fillFountain(new PowerWater());
    }
}
