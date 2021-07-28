package com.company;


public class Player {
    public String name;
    public int numberOfDice;
    public Cup cup = new Cup();

    public Player(String name, int numberOfDice) {
        this.name = name;
        this.numberOfDice = numberOfDice;
    }


}
