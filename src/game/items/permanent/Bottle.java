package game.items.permanent;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.consumption.DrinkAction;
import game.items.consumable.Water;
import game.utilities.Status;

import java.util.Stack;

public class Bottle extends Item {

    private final Stack<Water> bottle = new Stack<>();

    public Bottle() {
        super("Bottle", 'B', true);
        this.addAction(new DrinkAction(this));
    }

    @Override
    public DropItemAction getDropAction(Actor actor) {
        if(actor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            return null;
        }
        return super.getDropAction(actor);
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);
    }

    public void fill(Water liquid){
        bottle.push(liquid);
    }

    public Water drink(){
        return bottle.pop();
    }

    public String bottleContents(){
        return bottle.toString();
    }
}
