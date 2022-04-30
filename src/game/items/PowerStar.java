package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.PickUpStarAction;
import game.actors.Status;

public class PowerStar extends Item {

    private int timeSpan;
    private boolean consumed = false;

    public PowerStar() {
        super("Power Star", '*', true);
        timeSpan = 10;
    }

    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);

        if(timeSpan == 1){
            currentLocation.removeItem(this);}
        timeSpan--;
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);

        if(consumed) {
            if (timeSpan >= 1) {
                actor.addCapability(Status.INVINCIBLE);
                timeSpan--;
                return;
            }
            actor.removeCapability(Status.INVINCIBLE);
            actor.removeItemFromInventory(this);
        }
    }

    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return new PickUpStarAction(this);
    }

    @Override
    public DropItemAction getDropAction(Actor actor) {
        return null;
    }

    public int getTimeSpan() {
        return timeSpan;
    }

    public void consumeStar(){
        timeSpan = 10;
        consumed = true;
    }
}
