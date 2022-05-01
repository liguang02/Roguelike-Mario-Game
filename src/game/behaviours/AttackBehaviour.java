package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;

public class AttackBehaviour implements Behaviour {

    private final String direction;

    private final Actor target;

    public AttackBehaviour(String direction, Actor target) {
        this.direction = direction;
        this.target = target;
    }

    // TODO: develop and use it to attack the player automatically.
    @Override
    public Action getAction(Actor actor, GameMap map) {
        return new AttackAction(target, direction);
    }
}
