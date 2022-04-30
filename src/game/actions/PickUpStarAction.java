package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.PowerStar;

public class PickUpStarAction extends PickUpItemAction {

    private final PowerStar powerStar;
    /**
     * Constructor.
     *
     * @param powerStar the item to pick up
     */
    public PickUpStarAction(PowerStar powerStar) {
        super(powerStar);
        this.powerStar = powerStar;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        super.execute(actor, map);
        return new ConsumeStarAction(powerStar).execute(actor, map);
    }

    @Override
    public String menuDescription(Actor actor) {
        String menuOutput = actor + " consumes " + powerStar;
        menuOutput += " (" + powerStar. getTimeSpan() + " turns left)";
        return menuOutput;
    }
}
