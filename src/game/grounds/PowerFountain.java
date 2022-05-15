package game.grounds;

import game.items.PowerWater;

public class PowerFountain extends Fountain {

    public PowerFountain() {
        super('A', "Power Fountain");
        this.fillFountain(new PowerWater());
    }
}
