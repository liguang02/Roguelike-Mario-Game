package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.SuperMushroom;

/**
 * PickUpShroomAction class to pick up and consume super mushrooms from the ground.
 */
public class PickUpShroomAction extends PickUpItemAction {
    /**
     * SuperMushroom type object that the entire action will work off.
     */
    private final SuperMushroom superMushroom;

    /**
     * Constructor for PickUpShroomAction class
     * @param superMushroom A superMushroom type item
     */
    public PickUpShroomAction(SuperMushroom superMushroom) {
        super(superMushroom);
        this.superMushroom = superMushroom;
    }

    /**
     * Picks up and consumes the Super Mushroom item from the ground
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String description for actor consuming the super mushroom
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        super.execute(actor, map);
        return new ConsumeShroomAction(superMushroom).execute(actor, map);
    }

    /**
     * Describe the action in a format suitable for displaying in the menu.
     * @param actor The actor performing the action.
     * @return Menu string for player to select the option to pick up and consume mushroom from ground.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes " + superMushroom;
    }
}
