package game.grounds.fountains;

import game.items.consumable.PowerWater;

public class PowerFountain extends Fountain {

    public PowerFountain() {
        super('A', "Power Fountain");
        this.fillFountain(new PowerWater());
    }
}
