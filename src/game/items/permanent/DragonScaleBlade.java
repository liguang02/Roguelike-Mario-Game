package game.items.permanent;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.WeaponUpgradeAction;
import game.items.Purchasable;
import game.utilities.Status;

/**
 * The Dragon Scale Blade Weapon that is upgradeable and purchasable from Toad.
 * @version 1.0.0
 * @author sthi0011
 */
public class DragonScaleBlade extends WeaponItem implements Purchasable, UpgradeableWeapon {
    /**
     * An instance variable that stores the price value for Dragon Scale Blade
     */
    private final int price;
    /**
     * The value of buff for weapon damage if upgrade occurs
     */
    private final int damageBuff = 50;
    /**
     * Current total damage increase (accumulation of damage buffs from upgrades)
     */
    private int damageIncrease = 0;
    /**
     * The specific upgrade action for this weapon
     */
    private final Action upgradeAction;
    /**
     * Boolean variable to check if an upgrade action has been added for this weapon
     */
    private boolean actionAdded;

    /**
     * Constructor for the Dragon Scale Blade
     */
    public DragonScaleBlade(){
        super("Dragon Scale Blade", '⚔', 5, "slashes",100);
        price = 1000;
        this.addToPurchasableManager();
        upgradeAction = new WeaponUpgradeAction(this);
    }

    /**
     * Returns price of the blade
     * @return price
     */
    @Override
    public int price() {
        return price;
    }

    /**
     * If item is on ground, no upgrade action allowed. (action removed)
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        if(actionAdded){
            this.removeAction(upgradeAction);
            actionAdded = false;
        }
        super.tick(currentLocation);
    }

    /**
     * If item in inventory, upgrade action allowed (action added)
     * If item is BROKEN, no more upgrading.
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        this.removeAction(upgradeAction);
        actionAdded = false;
        if(!this.hasCapability(Status.BROKEN)){
            this.addAction(upgradeAction);
            actionAdded = true;
        }
        super.tick(currentLocation, actor);
    }

    /**
     * Returns the total damage this weapon causes on attack.
     * @return int, damage value
     */
    @Override
    public int damage() {
        return super.damage() + damageIncrease;
    }

    /**
     * The upgrade action for this Upgradeable weapon.
     * Hurts the actor upgrading the weapon by the damage buff value.
     * If actor dies when upgrading, upgrade cancelled and weapon buffs set back to 0.
     * @param actor The actor upgrading this weapon.
     * @return String of upgrade execution
     */
    @Override
    public String upgrade(Actor actor) {
         actor.hurt(damageBuff);
         damageIncrease += damageBuff;
         if(!actor.isConscious()){
             this.addCapability(Status.BROKEN);
             actor.heal(damageBuff);
             damageIncrease = 0;
             return actor + " failed to upgrade " + this + " is now BROKEN!";
         }
         return actor + " sacrifices " + this.upgradeCost() + " buffing " + this;
    }

    /**
     * String of upgrade cost for menu printing
     * @return String of the upgrade cost
     */
    @Override
    public String upgradeCost() {
        return damageBuff + "HP";
    }
}
