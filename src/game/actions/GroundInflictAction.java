package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import game.utilities.Status;

public class GroundInflictAction extends Action {
    private int damage;
    private Enum<Status> statusEnum;

    public GroundInflictAction(Enum<Status> statusEnum, int damage) {
        this.damage = damage;
        this.statusEnum = statusEnum;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Ground actorGround = map.locationOf(actor).getGround();
        if (actorGround.hasCapability(statusEnum)){
            actor.hurt(damage);
        }
        return actor + " loses " + damage + " for standing on the " + actorGround +  "!";
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
