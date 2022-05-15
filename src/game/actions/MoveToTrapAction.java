package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.utilities.Status;

public class MoveToTrapAction extends Action {
    private int damage;
    private Location moveToLocation;
    private String direction;
    private Enum<Status> statusEnum;

//    public MoveToTrapAction(Location moveToLocation, String direction){
//        this.moveToLocation = moveToLocation;
//        this.direction = direction;
//    }
    public MoveToTrapAction(int damage, Location moveToLocation, String direction) {
        this.damage = damage;
        this.moveToLocation = moveToLocation;
        this.direction = direction;
    }
    public MoveToTrapAction(Enum<Status> statusEnum, int damage){
        this.statusEnum = statusEnum;
        this.damage = damage;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
//        Ground actorGround = map.locationOf(actor).getGround();
        map.moveActor(actor, moveToLocation);
//        if (moveToLocation.getGround().hasCapability(statusEnum)){
//            actor.hurt(damage);
//            if(!actor.isConscious()){
//                actor.addCapability(Status.DEAD);
//            }
//        }
        return actor + " loses " + damage + " for standing on the " + moveToLocation.getGround() +  "!";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " moves " + direction + " " + moveToLocation.getGround();
    }
}
