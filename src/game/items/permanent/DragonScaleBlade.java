package game.items.permanent;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.SelfSacrificeAction;
import game.items.Purchasable;

public class DragonScaleBlade extends WeaponItem implements Purchasable, UpgradeableWeapon {

    private final int price;
    private final int damageBuff = 50;
    private int damageIncrease = 0;
    private final Action upgradeAction;
    private boolean actionAdded;

    public DragonScaleBlade(){
        super("Dragon Scale Blade", '⚔', 5, "slashes",100);
        price = 1000;
        this.addToPurchasableManager();
        upgradeAction = new SelfSacrificeAction(this);
    }

    @Override
    public int price() {
        return price;
    }

    @Override
    public void tick(Location currentLocation) {
        if(actionAdded){
            this.removeAction(upgradeAction);
            actionAdded = false;
        }
        super.tick(currentLocation);
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        if(!actionAdded){
            this.addAction(upgradeAction);
            actionAdded = true;
        }
        super.tick(currentLocation, actor);
    }

    @Override
    public int damage() {
        return super.damage() + damageIncrease;
    }

    @Override
    public void upgrade(Actor actor) {
         actor.hurt(damageBuff);
         damageIncrease += damageBuff;
    }

    @Override
    public String upgradeCost() {
        return damageBuff + " HP";
    }
}
