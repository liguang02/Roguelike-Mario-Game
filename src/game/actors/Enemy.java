package game.actors;

import edu.monash.fit2099.engine.actors.Actor;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.SuicideBehaviour;
import game.behaviours.WanderBehaviour;
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
    public Enemy(String name, char displayChar, int hitPoints){
        super(name, displayChar, hitPoints);
        this.addCapability(Status.ENEMY);
        this.registerInstance();
        behaviours.put(1, new WanderBehaviour());
    }

    @Override
    public void resetInstance() {
        this.addCapability(Status.REMOVED);
        behaviours.clear();
        behaviours.put(1, new SuicideBehaviour());
    }

    public Map<Integer, Behaviour> getBehaviours() {
        return behaviours;
    }
}
