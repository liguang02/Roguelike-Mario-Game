package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import game.actions.resetAction;
import game.items.WalletManager;
import game.reset.Resettable;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements Resettable {

	private final Menu menu = new Menu();
	private boolean resetAdded = false;

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		this.registerInstance();
		this.addCapability(Status.HOSTILE_TO_ENEMY);
	}

	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Actions

		Action resetAction = new resetAction();

		if(!this.hasCapability(Status.RESET) && !resetAdded){
			actions.add(resetAction);
			resetAdded = true;
		}

		if(this.hasCapability(Status.RESET)){
			actions.remove(resetAction);
			resetAdded = false;
		}

		displayDetalis(display, map);

		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	@Override
	public char getDisplayChar(){
		return this.hasCapability(Status.TALL) ? Character.toUpperCase(super.getDisplayChar()): super.getDisplayChar();
	}

	@Override
	public void resetInstance() {
		this.capabilitiesList().clear();
		this.heal(getMaxHp());
		this.capabilitiesList().add(Status.RESET);
	}

	public void displayDetalis(Display display, GameMap map){

		int x_coord = map.locationOf(this).x();
		int y_coord = map.locationOf(this).y();
		int wallet = WalletManager.getInstance().getBalance(this);
		String formatted = this.name + this.printHp();
		formatted = formatted + " at (" + x_coord + ", " + y_coord + ")" + "\n";
		formatted = formatted + "wallet: $" + wallet;
 		if(this.hasCapability(Status.INVINCIBLE)){
			formatted = formatted + "\n" + "Mario is INVINCIBLE!";
		}
		display.println(formatted);
	}
}
