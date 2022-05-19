package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.AttackAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.FollowBehaviour;
import game.items.permanent.Key;
import game.utilities.Status;

/**
 * Bowser enemy that is spawned on the BossMap.
 * @version 1.1.1
 * @author sthi0011, lcha0068, esea0003
 */
public class Bowser extends Enemy {

    private final Location defaultLocation;

    /**
     * Bowser constructor which contains the BOWSER capability (which
     * enables bowser to drop fire grounds in the AttackAction class) and
     * the FIRST_TURN capability which stores the location of Bowser for it
     * to be reset back to the original location during the first turn.
     */
    public Bowser(Location defaultLocation) {
        super("Bowser", 'B', 500);
        this.addCapability(Status.BOWSER);
        this.defaultLocation = defaultLocation;
    }

    /**
     * To check for any actions that can be performed by Bowser when it encounters another actor which
     * has HOSTILE_TO_ENEMY.
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return actions, a list of actions that can be performed by Bowser
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        this.addItemToInventory(new Key());

        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
            getBehaviours().remove(1);
            getBehaviours().put(2, new AttackBehaviour(direction, otherActor));
            getBehaviours().put(3, new FollowBehaviour(otherActor));
        }
        return actions;
    }

    /**
     * Register instance of the Bowser to be reset if mario resets the game (and move
     * to the default location stored from the first turn) and also
     * maximises hp once reset.
     */
    @Override
    public void resetInstance() {
        this.heal(getMaxHp());

        if(defaultLocation.containsAnActor()){
            Location newDestination = defaultLocation;
            for (Exit exit : defaultLocation.getExits()) {
                Location destination = exit.getDestination();
                if(!destination.containsAnActor()){
                    newDestination = destination;
                }
            }
            new MoveActorAction(newDestination, "").execute(defaultLocation.getActor(), newDestination.map());
        }
        new MoveActorAction(defaultLocation, "").execute(this,defaultLocation.map());
    }

    /**
     * playTurn is used after the player performs its action
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return Possible action for Bowser to carry out
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        Action deathAction = super.onDeath();
        if(deathAction != null){
            return deathAction;
        }
        for(game.behaviours.Behaviour Behaviour : getBehaviours().values()) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     * Enemy's default Attack action which returns the damage and verb of the attack
     * @return default IntrinsicWeapon
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(80, "punch");
    }



}
