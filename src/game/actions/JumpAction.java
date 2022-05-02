package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.utilities.Status;
import game.utilities.Probability;

/**
 * JumpAction class to do the jump actions
 * @version 1.1.2
 * @author sthi0011, lcha0068, esea0003
 */
public class JumpAction extends Action {
    /**
     * The success rate of the jump (0 - 100)
     */
    protected int successRate;
    /**
     * The damage taken if jump fails
     */
    protected int damage;
    /**
     * The direction of the jump
     */
    protected String direction;
    /**
     * The location of the jump destination
     */
    protected Location moveToLocation;

    /**
     * Constructor for the JumpAction
     * @param successRate The success rate of the jump (0 - 100)
     * @param damage The damage taken if jump fails
     * @param direction The direction of the jump
     * @param moveToLocation The location of the jump destination
     */
    public JumpAction(int successRate, int damage, String direction, Location moveToLocation) {
        this.moveToLocation = moveToLocation;
        this.successRate = successRate;
        this.damage = damage;
        this.direction = direction;
    }

    /**
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return  String to show the player can jump in the menu.
     */
    @Override
    public String execute(Actor actor, GameMap map){
        if (actor.hasCapability(Status.TALL)){
            successRate = 100;
        }
        if(Probability.success(successRate)){
            map.moveActor(actor, moveToLocation);
            // need to think of a way to get the ground name (currently it just prints the ground object)
            return "Player jumped to " + moveToLocation.getGround().toString();
        }else{
            actor.hurt(damage);
            if(!actor.isConscious()){
                actor.addCapability(Status.DEAD);
            }
            return  "Player failed jump, damage taken : " + damage;
        }
    }

    /**
     *
     * @param actor The actor performing the action.
     * @return The menu desciprtion of this jump action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " jumps " + direction + " " + moveToLocation.getGround().toString();
    }
}
