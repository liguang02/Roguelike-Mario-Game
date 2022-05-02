package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import game.actions.DeathAction;
import game.behaviours.Behaviour;
import game.behaviours.SuicideBehaviour;
import game.behaviours.WanderBehaviour;
import game.reset.ResetManager;
import game.reset.Resettable;
import game.utilities.Status;

import java.util.HashMap;
import java.util.Map;

/**
 * Enemy class which is used to set up the actors
 * @version 1.1.2
 * @author sthi0011, lcha0068, esea0003
 */
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

    /**
     * resetInstance for all enemies, adds status removed then killed by suicideBehaviour()
     * Clears all behaviours except for SuicideBehaviour
     */
    @Override
    public void resetInstance() {
        this.addCapability(Status.REMOVED);
        behaviours.clear();
        behaviours.put(1, new SuicideBehaviour());
    }

    /**
     * Removed the reset instance from the manager if killed naturally.
     */
    public void cleanReset(){
        ResetManager.getInstance().cleanUp(this);
    }

    /**
     * If the enemy is killed normally.
     * Clean up reset manager, don't want it to do useless actions (does not crash game even without this)
     */
    public Action onDeath() {
        if (this.hasCapability(Status.DEAD)) {
            cleanReset();
            return new DeathAction();
        }
        return null;
    }

    public Map<Integer, Behaviour> getBehaviours() {
        return behaviours;
    }

}
