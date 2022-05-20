package game.items.permanent;

import edu.monash.fit2099.engine.actors.Actor;

public interface UpgradeableWeapon {

    void upgrade(Actor actor);

    String upgradeCost();
}
