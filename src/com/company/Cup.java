package com.company;

import java.util.ArrayList;

public class Cup {
    public ArrayList<Die> dice = new ArrayList<>();
    public Die die = new Die(6);


    public void addDice(int numOfDice) {
        while (dice.size() < numOfDice) {
            dice.add(die);
        }
        System.out.println("Cup now holds" + dice.size() + " dice");
    }

    public void removeDie() {
        if (dice.size() > 0) {
            dice.remove(0);
        }
        System.out.println("You lost a Die!");
    }

    public String rollDice() {

        String returnValue = "Your hand of Dice: ";

        for (Die die : dice) {
            returnValue += die.roll() + ", ";
        }

        return returnValue.trim();
    }
}
