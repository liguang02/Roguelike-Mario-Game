package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.PowerStar;

/**
 * PickUpStarAction class to pick up and consume power stars from the ground.
 * @version 1.1.2
 * @author sthi0011, lcha0068, esea0003
 */
public class PickUpStarAction extends PickUpItemAction {
    /**
     * PowerStar type object that the entire action will work off.
     */
    private final PowerStar powerStar;

    /**
     * Constructor for PickUpStarAction class
     * @param powerStar A PowerStar type item
     */
    public PickUpStarAction(PowerStar powerStar) {
        super(powerStar);
        this.powerStar = powerStar;
    }

    /**
     * Picks up and consumes the Super Mushroom item from the ground
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String description for actor consuming the power star
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        super.execute(actor, map);
        return new ConsumeStarAction(powerStar).execute(actor, map);
    }

    /**
     * Describe the action in a format suitable for displaying in the menu.
     * @param actor The actor performing the action.
     * @return Menu string for player to select the option to pick up and consume star from ground
     */
    @Override
    public String menuDescription(Actor actor) {
        String menuOutput = actor + " picks up and consumes " + powerStar;
        menuOutput += " (" + powerStar. getTimeSpan() + " turns left)";
        return menuOutput;
    }
}
