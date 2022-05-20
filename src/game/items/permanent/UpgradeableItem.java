package game.items.permanent;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Interface for upgradeable items
 * @version 1.0.0
 * @author sthi0011
 */
public interface UpgradeableItem {

    /**
     * Method to implement in each upgradeable item, the method to be executed on upgrade
     * @param actor The actor upgrading this weapon.
     * @return String of upgrade execution
     */
    String upgrade(Actor actor);

    /**
     * Method to implement in each upgradeable item, String of upgrade cost for menu printing
     * @return String of the upgrade cost
     */
    String upgradeCost();
}
