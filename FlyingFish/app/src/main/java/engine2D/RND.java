package engine2D;

import java.util.Random;

public class RND {

    private static final Random r = new Random();

    public static int getRandom(int max){
        return r.nextInt(max);
    }

    public static int getRangeRandom(int min, int max){
        return r.nextInt(max - min + 1) + min;
    }

    public static double getRandomDouble(double max){
        return r.nextDouble() * max;
    }

    public static double getRangeRandomDouble(double min, double max){
        return r.nextDouble() * (max - min) + min;
    }

    // boolean als rückgabe
    public static boolean occured(double value){
        return (r.nextDouble() < value);
    }

}
