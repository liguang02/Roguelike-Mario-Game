package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.reset.ResetManager;
import game.actors.Status;

public class resetAction extends Action {

    @Override
    public String execute(Actor actor, GameMap map) {
        ResetManager resetInstance = ResetManager.getInstance();
        resetInstance.run();
        actor.addCapability(Status.RESET);
        return "Reset Occurred";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "RESET";
    }

    @Override
    public String hotkey() {
        return "r";
    }
}
