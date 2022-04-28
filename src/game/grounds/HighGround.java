package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.JumpAction;
import game.items.Coin;
import game.items.ItemCapabilities;

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
            if(location.getActor().hasCapability(ItemCapabilities.INVINCIBLE)){
                location.setGround(new Dirt());
                location.addItem(new Coin(5));
            }
        }
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(ItemCapabilities.INVINCIBLE);
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actionList = new ActionList();
        if(!actor.hasCapability(ItemCapabilities.INVINCIBLE)){
            actionList.add(new JumpAction(successRate,damage, direction, location));
        }
        return actionList;
    }
}