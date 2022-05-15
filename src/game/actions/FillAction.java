package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.grounds.Fountain;
import game.items.Bottle;
import game.items.Water;

public class FillAction extends Action {

    private final Bottle bottle;
    private final Fountain fountain;

    public FillAction(Bottle bottle, Fountain fountain) {
        this.bottle = bottle;
        this.fountain = fountain;
    }


    @Override
    public String execute(Actor actor, GameMap map) {
        String returnString = menuDescription(actor);
        Water water = fountain.removeWater();
        if(water != null){
            bottle.fill(water);
            return returnString;
        }
        return fountain + " has dried up. :(";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " refill " + fountain.fountainLiquid();
    }
}
