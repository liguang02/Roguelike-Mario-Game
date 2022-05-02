package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;

/**
 * AttackBehaviour is used by enemies, to attack any actor which is directly next to the
 * target (typically the player)
 * @version 1.1.2
 * @author sthi0011, lcha0068, esea0003
 */
public class AttackBehaviour implements Behaviour {

    private final String direction;

    private final Actor target;

    /**
     * To store the direction and the target
     * @param direction the direction of the target
     * @param target the target actor
     */
    public AttackBehaviour(String direction, Actor target) {
        this.direction = direction;
        this.target = target;
    }

    /**
     * To attack the actor automatically
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return An attackAction
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        return new AttackAction(target, direction);
    }
}
