package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.permanent.UpgradeableItem;

/**
 * WeaponUpgradeAction to upgrade weapons.
 * @version 1.0.0
 * @author sthi0011
 */
public class WeaponUpgradeAction extends Action {
    /**
     * The weapon to be upgraded.
     */
    private final UpgradeableItem weapon;

    /**
     * Constructor for WeaponUpgradeAction
     * @param weapon The weapon to be upgraded.
     */
    public WeaponUpgradeAction(UpgradeableItem weapon){
        this.weapon = weapon;
    }

    /**
     * Executes the upgrade based on the weapons upgrade implementation
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return Upgrade action string
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return weapon.upgrade(actor);
    }

    /**
     * Prints upgrade action description with its cost.
     * @param actor The actor performing the action.
     * @return String for menu description of upgrade.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " upgrades " + weapon + " (Cost: " + weapon.upgradeCost() + ")";
    }
}
