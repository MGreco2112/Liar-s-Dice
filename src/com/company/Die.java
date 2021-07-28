package com.company;

import java.util.Random;

public class Die {
    public int numberOfSides;
    public int faceUpValue;

    public Die(int numberOfSides) {
        this.numberOfSides = numberOfSides;
        faceUpValue = 0;
    }

    public int roll() {
        Random random = new Random();

        int roll = random.nextInt(numberOfSides) + 1;

        faceUpValue = roll;

        return getFaceUpValue();
    }

    public int getFaceUpValue() {
        return faceUpValue;
    }
}
