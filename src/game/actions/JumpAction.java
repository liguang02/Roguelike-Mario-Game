package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Probability;


public class JumpAction extends Action {

    protected int successRate;
    protected int damage;
    protected String direction;
    protected Location moveToLocation;

    public JumpAction(int successRate, int damage, String direction, Location moveToLocation) {
        this.moveToLocation = moveToLocation;
        this.successRate = successRate;
        this.damage = damage;
        this.direction = direction;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if(Probability.success(successRate)){
            map.moveActor(actor, moveToLocation);
            // need to think of a way to get the ground name (currently it just prints the ground object)
            return "Player jumped to " + moveToLocation.getGround().toString();
        }else{
            actor.hurt(damage);
            return  "Player failed jump, damage taken : " + damage;
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + " jumps " + direction;
    }
}
