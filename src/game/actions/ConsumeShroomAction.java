package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Status;
import game.items.SuperMushroom;

public class ConsumeShroomAction extends Action {

    private final SuperMushroom superMushroom;

    public ConsumeShroomAction(SuperMushroom superMushroom) {
        this.superMushroom = superMushroom;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        actor.increaseMaxHp(50);
        actor.addCapability(Status.TALL);
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes " + superMushroom;
    }
}
