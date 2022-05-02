
package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.JumpAction;
import game.actors.Status;
import game.items.Coin;

/**
 * @version 1.1.2
 * @author sthi0011, lcha0068, esea0003
 * HighGround class is an abstract class extending Ground. Player can jump to any HighGround classes.
 */
public abstract class HighGround extends Ground {
    /**
     * successRate of the player jumping to the high ground
     */
    private final int successRate;
    /**
     * the fall damage if player fails to jump
     */
    private final int damage;

    /**
     * Constructor for HighGround class
     *  @param displayChar character to display for this type of terrain
     */
    public HighGround(char displayChar, int successRate, int damage) {
        super(displayChar);
        this.successRate = successRate;
        this.damage = damage;
    }

    /**
     * This method will check if the player has the power star buff active, then it will
     * destroy all the high ground objects and spawns a coin on its location.
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        if (location.getActor() != null) {
            if(location.getActor().hasCapability(Status.INVINCIBLE)){
                //Calling the tick method of the tree before it gets destroyed to clean Reset Manager.
                location.getGround().addCapability(Status.REMOVED);
                //Setting the dirt where the tree was.
                location.setGround(new Dirt());
                location.addItem(new Coin(5));
            }
        }
    }

    /**
     * If the actor has the power star buff active, this method allows the actor to enter
     * its ground location.
     * @param actor the Actor to check
     * @return True if player has power star buff; false if otherwise
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.INVINCIBLE);
    }

    /**
     * This method will add the JumpAction into the actionlist if the actor does not have any
     * power star buff active and no other actor is currently standing on the current location
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return an actionlist containing JumpAction if it passes the check
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actionList = new ActionList();
        if(!actor.hasCapability(Status.INVINCIBLE) && location.getActor() != actor){
            actionList.add(new JumpAction(successRate,damage, direction, location));
        }
        return actionList;
    }

    /**
     * a method that will return the name of the highground. e.g Wall, Mature, Sprout etc.
     * @return String representing the name of the high ground object.
     */
    public abstract String toString();
}