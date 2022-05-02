package game.actions.speech;
import edu.monash.fit2099.engine.actors.Actor;
import game.actors.Status;

import java.util.ArrayList;

public class Monologue {
    /**
     * List of all monologues
     * @version 1.1.2
     * @author sthi0011, lcha0068, esea0003
     */
    private final ArrayList<String> allMonologues;
    /**
     * List of all keys to identify monologues
     */
    private final ArrayList<Enum<Status>> keyMonologues;

    /**
     * Constructor for monologue class
     * Initializes the 2 arraylists
     */
    public Monologue() {
        this.allMonologues = new ArrayList<>();
        this.keyMonologues = new ArrayList<>();
    }


    /**
     * Adding all the actors monologues to the arraylist.
     * @param identifier The specific situation where the monologue can or cannot be said (Enum Status type)
     * @param monologue The message that the actor would say when spoken to.
     */
    public void addMonologues(Enum<Status> identifier, String monologue) {
        allMonologues.add(monologue);
        keyMonologues.add(identifier);
    }

    /**
     * All the monologues the actor has
     * @return all monologues in a list.
     */
    public ArrayList<String> getAllMonologues() {
        return allMonologues;
    }

    /**
     * Returns the monologues that can be said based on the actors capabilities
     * Has power star buff active : Removes the Power Star monologue
     * Has a wrench : Removes wrench monologue
     * @param player The player actor who can speak to toad
     * @return List of possible strings that toad can say
     */
    public ArrayList<String> getToadMonologues(Actor player) {

        ArrayList<String> tempArray = new ArrayList<>();
        ArrayList<String> returnArray = new ArrayList<>();

        for (int i = 0; i < keyMonologues.size(); i++) {

            tempArray.add(allMonologues.get(i));

            if (player.hasCapability(Status.INVINCIBLE)) {
                if (keyMonologues.get(i) == Status.INVINCIBLE) {
                    tempArray.set(i, "Removed");
                }
            }
            if (player.hasCapability(Status.WRENCH)) {
                if (keyMonologues.get(i) == Status.WRENCH) {
                    tempArray.set(i, "Removed");
                }
            }
        }
        tempArray.forEach(x -> {
            if(!x.equals("Removed")){
                returnArray.add(x);
            }
        });

        return returnArray;
    }
}
