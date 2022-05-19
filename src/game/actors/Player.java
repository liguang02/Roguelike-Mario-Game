package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.DeathAction;
import game.actions.ResetAction;
import game.items.BottleManager;
import game.items.WalletManager;
import game.items.permanent.Bottle;
import game.reset.Resettable;
import game.utilities.Status;
/**
 * Class representing the Player.
 * @version 1.1.2
 * @author sthi0011, lcha0068, esea0003
 */
public class Player extends Actor implements Resettable {
	/**
	 * The menu to display for player.
	 */
	private final Menu menu = new Menu();
	/**
	 * Attack value of player without any weapons
	 */
	private int intrinsicAttackValue;
	/**
	 * Attack verb of player without any weapons
	 */
	private final String intrinsicAttackVerb;
	/**
	 * Constructor for Player class.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hit points
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		addBottle();
		this.registerInstance();
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.intrinsicAttackValue = super.getIntrinsicWeapon().damage();
		this.intrinsicAttackVerb = super.getIntrinsicWeapon().verb();
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

		//If player is dead, new Death Action (remove from map and drop items from inventory)
		if(this.hasCapability(Status.DEAD)){
			return new DeathAction();
		}
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
	 * Method that returns the intrinsic weapon of the actor.
	 * Override to allow for buff in damage when consuming the Power Water.
	 * Avoids directly affecting the original getIntrinsicWeapon to allow for reset (removing all the buff damage)
	 * @return a freshly-instantiated IntrinsicWeapon
	 */
	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		if(this.hasCapability(Status.INTRINSIC_BUFF)){
			intrinsicAttackValue += 15;
			this.removeCapability(Status.INTRINSIC_BUFF);
		}
		return new IntrinsicWeapon(intrinsicAttackValue, intrinsicAttackVerb);
	}

	/**
	 * Adds the capability for the player to consume stars/mushrooms
	 * For future proofing : in a situation where actor cannot consume an item given condition we can just have an if statement here before add capability
	 */
	public void addCapabilities(){
		this.addCapability(Status.CONSUMER_STAR);
		this.addCapability(Status.CONSUMER_SHROOM);
		this.addCapability(Status.CONSUMER_FIREPOT);
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
	 * Gives' player RESET Capability to tell that the player cannot reset again.
	 */
	@Override
	public void resetInstance() {
		this.capabilitiesList().forEach(this::removeCapability);
		this.heal(getMaxHp());
		this.addCapability(Status.RESET);
		this.intrinsicAttackValue = super.getIntrinsicWeapon().damage();
	}

	/**
	 * Adds a bottle to the inventory for this actor
	 */
	public void addBottle(){
		Bottle bottle = new Bottle();
		BottleManager.getInstance().addBottle(this, bottle);
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
		display.println(this.getInventory().toString());
		display.println(formatted);
	}
}
