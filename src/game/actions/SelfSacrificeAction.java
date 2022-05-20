package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.permanent.UpgradeableWeapon;

public class SelfSacrificeAction extends Action {

    private final UpgradeableWeapon weapon;

    public SelfSacrificeAction(UpgradeableWeapon weapon){
        this.weapon = weapon;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        weapon.upgrade(actor);
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " sacrifice " + weapon.upgradeCost() + " buffing " + weapon;
    }
}
