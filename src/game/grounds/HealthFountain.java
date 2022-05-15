package game.grounds;

import game.items.HealingWater;

public class HealthFountain extends Fountain{

    public HealthFountain() {
        super('H', "Health Fountain");
        this.fillFountain(new HealingWater());
    }
}
