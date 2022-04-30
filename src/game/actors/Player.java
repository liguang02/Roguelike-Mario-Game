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

		if(!this.hasCapability(Status.RESET)){
			actions.add(resetAction);
		}

		if(this.hasCapability(Status.RESET)){
			actions.remove(resetAction);
		}

		displayDetails(display, map);

		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	@Override
	public char getDisplayChar(){
		char displayChar = super.getDisplayChar();
		if (this.hasCapability(Status.INVINCIBLE)){
			displayChar = 'i';
		}
		if(this.hasCapability(Status.TALL)){
			displayChar = Character.toUpperCase(displayChar);
		}
		return displayChar;
	}

	@Override
	public void resetInstance() {
		this.capabilitiesList().forEach(this::removeCapability);
		this.heal(getMaxHp());
		this.addCapability(Status.RESET);
	}

	public void displayDetails(Display display, GameMap map){

		int x = map.locationOf(this).x();
		int y = map.locationOf(this).y();
		int wallet = WalletManager.getInstance().getBalance(this);
		String formatted = this.name + this.printHp();
		formatted = formatted + " at (" + x + ", " + y + ")" + "\n";
		formatted = formatted + "wallet: $" + wallet;
		if(this.hasCapability(Status.INVINCIBLE)){
			formatted += "\n" + this + " is INVINCIBLE!";
		}
		display.println(this.getInventory().toString());
		display.println(this.capabilitiesList().toString());
		display.println(formatted);
	}
}
