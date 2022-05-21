package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import game.actions.GroundChangeAction;

/**
 * Behaviour of enemies destroying ground near them.
 * @version 1.0.0
 * @author sthi0011
 */
public class GroundDestroyBehaviour implements Behaviour {
    /**
     * The ground set by the actor using this behaviour
     */
    private final Ground newGround;
    /**
     * Counter for placing the behaviour every 5 turns
     */
    private int counter = 0;

    /**
     * Constructor for GroundDestroyBehaviour
     * @param newGround the ground to be set
     */
    public GroundDestroyBehaviour(Ground newGround) {
        this.newGround = newGround;
    }

    /**
     *
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return New GroundChangeAction every 5 turns else null
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        counter++;
        if((counter - 1) % 5 == 0){
            return new GroundChangeAction(newGround);
        }
        return null;
    }
}
