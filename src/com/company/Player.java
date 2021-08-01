package com.company;

public class Player {
    public int numberOfDice;
    public boolean isOut;
    public String name;
    public Cup cup = new Cup();

    public Player(String name, int numberOfDice) {
        this.name = name;
        this.numberOfDice = numberOfDice;
        cup.addDice(numberOfDice);
    }

    public void checkCondition() {
        isOut = cup.dice.size() <= 0;
    }

}
