package com.company;

//TODO Add bet() and callLie() methods. callLie() Method ends round, resulting in either the liar or caller losing a die
public class Player {
    public String name;
    public int numberOfDice;
    public Cup cup = new Cup();

    public Player(String name, int numberOfDice) {
        this.name = name;
        this.numberOfDice = numberOfDice;
    }

}
