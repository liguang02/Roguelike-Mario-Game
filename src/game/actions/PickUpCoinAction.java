package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Coin;
import game.items.WalletManager;

/**
 * PickUpCoinActions is a subclass of PickUpItemAction, this class will handle the operation of adding the value
 * correctly into the actor's wallet inside WalletManager class after picking up the coin.
 * @version 1.1.2
 * @author sthi0011, lcha0068, esea0003
 */
public class PickUpCoinAction extends PickUpItemAction {
    /**
     * the value of the coin
     */

    private final int coinValue;
    /**
     * A coin object
     */
    private final Coin coin;
    /**
     * Constructor for PickUpCoinAction
     *
     * @param coin the item to pick up
     */
    public PickUpCoinAction(Coin coin) {
        super(coin);
        this.coin = coin;
        this.coinValue = coin.getValue();
    }

    /**
     * Add the coin to the actor's wallet in WalletManager class
     *
     * @see Action#execute(Actor, GameMap)
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a suitable description to display in the UI
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.locationOf(actor).removeItem(coin);
        WalletManager.getInstance().addBalance(actor,coinValue);
        return menuDescription(actor);
    }

    /**
     * Describe the action in a format suitable for displaying in the menu.
     *
     * @see Action#menuDescription(Actor)
     * @param actor The actor performing the action.
     * @return a string, e.g. "Player picks up the coin"
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " picks up $" + coinValue;
    }
}