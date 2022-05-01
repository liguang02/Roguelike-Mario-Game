package game;

import game.actors.Toad;

import java.util.ArrayList;

public class Monologues extends Toad {

    public ArrayList AllMonologues(){
        ArrayList<String> allMonologues = new ArrayList<>();
        allMonologues.add("You might need a wrench to smash Koopa's hard shells.");
        allMonologues.add("You better get back to finding the Power Stars.");
        allMonologues.add("The Princess is depending on you! You are our only hope.");
        allMonologues.add("Being imprisoned in these walls can drive a fungus crazy :(");
        return allMonologues;
    }

    public ArrayList<String> getAllMonologues() {
        return AllMonologues();
    }

}
