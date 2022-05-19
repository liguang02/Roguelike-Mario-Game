package game.items.permanent;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.pickups.PickUpKeyAction;
import game.reset.Resettable;
import game.utilities.Status;

public class Key extends Item implements Resettable {
    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    private boolean removed = false;


    public Key() {
        super("Key", 'k', true);
        this.registerInstance();
    }

    @Override
    public void resetInstance() {
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);
        if (removed) {
            actor.addCapability(Status.KEY);
            actor.removeItemFromInventory(this);
        }
    }

    public void removeKey(){
        removed = true;
        this.addCapability(Status.REMOVED);
    }

    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return new PickUpKeyAction(this);
    }

    @Override
    public DropItemAction getDropAction(Actor actor) {
        if(!actor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            return super.getDropAction(actor);
        }
        return null;
    }
}