package game.grounds.fountains;

import game.items.consumable.HealingWater;

public class HealthFountain extends Fountain {

    public HealthFountain() {
        super('H', "Health Fountain");
        this.fillFountain(new HealingWater());
    }
}
