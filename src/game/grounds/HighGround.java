package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.JumpAction;
import game.actors.Status;
import game.items.Coin;

public abstract class HighGround extends Ground {

    private final int successRate;
    private final int damage;

    /**
     * Constructor.
     *  @param displayChar character to display for this type of terrain
     */
    public HighGround(char displayChar, int successRate, int damage) {
        super(displayChar);
        this.successRate = successRate;
        this.damage = damage;
    }


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

    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.INVINCIBLE);
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actionList = new ActionList();
        if(!actor.hasCapability(Status.INVINCIBLE) && location.getActor() != actor){
            actionList.add(new JumpAction(successRate,damage, direction, location));
        }
        return actionList;
    }
    public abstract String toString();
}