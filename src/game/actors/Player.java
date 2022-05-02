package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import game.actions.ResetAction;
import game.items.WalletManager;
import game.reset.Resettable;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements Resettable {
	/**
	 * The menu to display for player.
	 */
	private final Menu menu = new Menu();

	/**
	 * Constructor for Player class.
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

	/**
	 * Occurs at the start of every turn, adds capabilities to the player, checks for all actions player could have and displays the details/menu
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return Displaying the menu for user to choose actions from.
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		addCapabilities();
		addResetAction(actions);
		displayDetails(display, map);
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();
		return menu.showMenu(this, actions, display);
	}

	/**
	 * Gets the Display Character for the player to be placed on map
	 * Override to add characters for INVINCIBLE buff (Power Star) and TALL buff (Super Mushroom)
	 * Character details :
	 * 	Character 'i' : Player actor is INVINCIBLE (power star consumed)
	 * 	Capital characters (e.g. : M, I) : Player actor is TALL (super mushroom consumed)
	 */
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

	/**
	 * Adds the capability for the player to consume stars/shrooms
	 * For future proofing : in a situation where actor cannot consume an item given condition we can just have an if statement here before add capability
	 */
	public void addCapabilities(){
		this.addCapability(Status.CONSUMER_STAR);
		this.addCapability(Status.CONSUMER_SHROOM);
	}

	/**
	 * Provides player option to reset if it has not occurred, else removes that option.
	 * For future proofing : in a situation where actor should not be allowed to reset even if they have not used it.
	 * @param actions Actors action list giving them the options in menu.
	 */
	public void addResetAction(ActionList actions){
		Action resetAction = new ResetAction();
		if(!this.hasCapability(Status.RESET)){
			actions.add(resetAction);
		}
	}

	/**
	 * resetInstance for the player, removes all buffs/de-buffs from capabilities list
	 * Heals the player to full
	 * Gives player RESET Capability to tell that the player cannot reset again.
	 */
	@Override
	public void resetInstance() {
		this.capabilitiesList().forEach(this::removeCapability);
		this.heal(getMaxHp());
		this.addCapability(Status.RESET);
	}

	/**
	 * Displays miscellaneous details of the player such as current HP, coordinates, remaining money and statuses.
	 * @param display the I/O object to which messages may be written
	 * @param map the map containing the Actor
	 */
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
		// Print inventory (not needed for final submission, just easier to see what we are doing)
		// display.println(this.getInventory().toString());
		// Print buff list (not needed for final submission, just easier to see what we are doing)
		// display.println(this.capabilitiesList().toString());
		display.println(formatted);
	}
}
