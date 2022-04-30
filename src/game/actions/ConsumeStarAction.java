package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Status;
import game.items.PowerStar;

public class ConsumeStarAction extends Action {

    private final PowerStar powerStar;

    public ConsumeStarAction(PowerStar powerStar){
        this.powerStar = powerStar;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        actor.heal(200);
        actor.addCapability(Status.INVINCIBLE);
        powerStar.consumeStar();
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
            return actor + " consumes " + powerStar;
        }
}
