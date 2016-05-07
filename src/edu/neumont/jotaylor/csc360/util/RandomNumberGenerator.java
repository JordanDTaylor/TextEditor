package edu.neumont.jotaylor.csc360.util;

import java.util.Random;

public final class RandomNumberGenerator extends Random{

    private static RandomNumberGenerator instance;

    private RandomNumberGenerator() {
    }

    public static RandomNumberGenerator getInstance(){

        if(instance == null){
            instance = new RandomNumberGenerator();
        }

        return instance;
    }


}
