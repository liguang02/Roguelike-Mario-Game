package game;

import java.util.Random;

public class Probability {
    public static boolean success(int chance){
        return new Random().nextDouble() <= chance/100.0;
    }
}
