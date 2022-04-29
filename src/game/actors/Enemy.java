package game.actors;

import edu.monash.fit2099.engine.actors.Actor;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.SuicideBehaviour;
import game.items.ItemCapabilities;
import game.reset.Resettable;

import java.util.HashMap;
import java.util.Map;

public abstract class Enemy extends Actor implements Resettable {
    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour
    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    // is there any way to remove the actor argument? Cuz koopa and goomba should have 0 argument input. mY sugesstion: create default constructors (i added one
    // but im not sure.) -LG
    public Enemy(String name, char displayChar, int hitPoints){
        super(name, displayChar, hitPoints);
    }
    public Enemy(String name, char displayChar, int hitPoints, Actor actor) {
        super(name, displayChar, hitPoints);
        this.registerInstance();
//        this.addCapability(Status.ENEMY);
        behaviours.put(11,new FollowBehaviour(actor));
        behaviours.put(12, new SuicideBehaviour());
    }


    @Override
    public void resetInstance() {
        this.hurt(this.getMaxHp());
        this.addCapability(Status.REMOVED);
    }

    public Map<Integer, Behaviour> getBehaviours() {
        return behaviours;
    }
}
