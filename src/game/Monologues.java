package game;
import java.util.HashMap;

public class Monologues {

    /**
     * Stores all the possible Monologues of the game - regardless of Mario having any special
     * effects that
     * @return
     */
    public HashMap AllMonologues(){
        HashMap<Integer, String> allMonologues = new HashMap<>();
        allMonologues.put(0, "You might need a wrench to smash Koopa's hard shells.");
        allMonologues.put(1, "You better get back to finding the Power Stars.");
        allMonologues.put(2, "The Princess is depending on you! You are our only hope.");
        allMonologues.put(3, "Being imprisoned in these walls can drive a fungus crazy :(");
        return allMonologues;
    }

    public HashMap<Integer, String> getAllMonologues() {
        return AllMonologues();
    }

}
