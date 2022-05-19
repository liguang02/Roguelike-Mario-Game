package game.items.consumable;

/**
 * Abstract class Water to be extended by any consumable liquids (such as the one in the fountain)
 * @version 1.1.2
 * @author sthi0011
 */
public abstract class Water implements Consumable {
    /**
     * String of the name of the water
     */
    private final String name;

    /**
     * Constructor for the water, just ot set the name
     * @param name The string of the name of the water
     */
    public Water(String name) {
        this.name = name;
    }

    /**
     * Method returning the name of the water as string
     * @return String of the name of the water
     */
    public String toString(){
        return name;
    }
}
