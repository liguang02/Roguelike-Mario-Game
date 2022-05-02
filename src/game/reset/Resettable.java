package game.reset;

/**
 * Resettable Interface for all items/actors/grounds that can be reset.
 * @version 1.1.2
 * @author sthi0011, lcha0068, esea0003
 */
public interface Resettable {
    /**
     * Allows any classes that use this interface to reset abilities, attributes, and/or items.
     */
    void resetInstance();

    /**
     * a default interface method that register current instance to the Singleton manager.
     * It allows corresponding class uses to be affected by global reset
     */
    default void registerInstance(){
        ResetManager.getInstance().appendResetInstance(this);
    }
}
