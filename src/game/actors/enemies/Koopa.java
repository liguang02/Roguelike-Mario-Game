package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.AttackAction;
import game.actions.DormantAttackAction;
import game.behaviours.*;
import game.items.consumable.SuperMushroom;
import game.items.permanent.Wrench;
import game.utilities.Status;

/**
 * The Koopa enemy.
 * @version 1.1.2
 * @author sthi0011, lcha0068, esea0003
 */
public class Koopa extends KoopaTroop{

    /**
     * A constructor to generate an instance of a Koopa which has a SuperMushroom in its inventory
     * to be dropped when the Koopa is destroyed.
     */
    public Koopa() {
        super("Koopa", 'K', 50);
    }
}
