package game;

import java.util.Random;

/**
 * A utility class that helps to perform a randomized check
 */
public class Probability {
    /**
     * This method will use the nextDouble() from Random module to obtain a randomized value between 0 and 1
     * and we will use it to compare with the input value/100.
     * @param chance the probability chance
     * @return True if it successfully passes the random check; False otherwise
     */
    public static boolean success(int chance){
        return new Random().nextDouble() <= chance/100.0;
    }
}
