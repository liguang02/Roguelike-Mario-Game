package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.SuperMushroom;

public class PickUpShroomAction extends PickUpItemAction {

    private final SuperMushroom superMushroom;

    /**
     * Constructor.
     *
     * @param superMushroom the item to pick up
     */
    public PickUpShroomAction(SuperMushroom superMushroom) {
        super(superMushroom);
        this.superMushroom = superMushroom;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        super.execute(actor, map);
        return new ConsumeShroomAction(superMushroom).execute(actor, map);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes " + superMushroom;
    }
}
