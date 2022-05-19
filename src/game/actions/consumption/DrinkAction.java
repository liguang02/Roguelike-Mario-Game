package game.actions.consumption;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.permanent.Bottle;
import game.items.consumable.Water;

/**
 * @version 1.0.0
 * @author sthi0011
 * A DrinkAction for the player to drink from the bottle.
 */
public class DrinkAction extends Action {
    /**
     * The actors bottle to drink from.
     */
    private final Bottle bottle;

    /**
     * Constructor for the drink action.
     * @param bottle A bottle object for the actor to drink from.
     */
    public DrinkAction(Bottle bottle) {
        this.bottle = bottle;
    }

    /**
     * Consuming a water object from the bottle if bottle not empty.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String of the drink action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Water liquid = bottle.drink();
        if(liquid != null){
            liquid.consume(actor);
            return actor + " consumes " + liquid;
        }
        return actor + " tried to drink from an EMPTY bottle... EMPTY!!";
    }

    /**
     * Returns menu description of DrinkAction.
     * @param actor The actor performing the action.
     * @return String of the menu description of the drink action alongside its contents
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " drinks from " + bottle + " " + bottle.bottleContents();
    }
}
