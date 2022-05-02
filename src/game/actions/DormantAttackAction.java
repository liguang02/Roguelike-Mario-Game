package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.actors.Status;

public class DormantAttackAction extends AttackAction {

    /**
     * Constructor for AttackAction class
     *
     * @param target    the Actor to attack
     * @param direction The string of direction of attack (North, South ...)
     */
    public DormantAttackAction(Actor target, String direction) {
        super(target, direction);
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        //Getting the attackers weapon
        Weapon weapon = actor.getWeapon();

        //If the attacker misses the target return string misses
        if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
            return actor + " misses " + target + " shell.";
        }

        target.addCapability(Status.DEAD);

        return actor + " destroys " + target + " shell using " + weapon;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " destroys " + target + " shell" + " at " + direction;
    }
}
