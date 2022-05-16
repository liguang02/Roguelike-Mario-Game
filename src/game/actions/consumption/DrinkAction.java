package game.actions.consumption;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.permanent.Bottle;
import game.items.consumable.Water;

public class DrinkAction extends Action {

    private final Bottle bottle;

    public DrinkAction(Bottle bottle) {
        this.bottle = bottle;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        try{
            Water liquid = bottle.drink();
            liquid.consume(actor);
            return actor + " consumes " + liquid;
        }
        catch (Exception e){
            return "Why did you drink from any empty bottle?";
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes " + bottle + " " + bottle.bottleContents();
    }
}
