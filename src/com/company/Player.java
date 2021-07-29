package com.company;

//TODO Add bet() and callLie() methods. callLie() Method ends round, resulting in either the liar or caller losing a die
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
