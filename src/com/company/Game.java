package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    public int numberOfPlayers;
    public int betDieValue;
    public int betNumberOfDice;
    public Player activePlayer;
    public Player lastBetter;
    public Table table = new Table();
    private final Scanner scanner = new Scanner(System.in);
    public ArrayList<Player> players = new ArrayList<>();


    public Game(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public void addPlayer(Player player) {
        if (players.size() < numberOfPlayers) {
            players.add(player);
        }
    }


    //TODO Finish round() method
    public void round() {
        for (Player player : players) {
            player.cup.rollDice();

            for (Die die : player.cup.dice) {
                if (table.tableDice.containsKey(die.faceUpValue)) {
                    int dieCount = table.tableDice.get(die.faceUpValue);
                    dieCount++;
                    table.tableDice.put(die.faceUpValue, dieCount);
                } else {
                    table.tableDice.put(die.faceUpValue, 1);
                }
            }
        }

        for (Player player : players) {
            activePlayer = player;
            System.out.println(activePlayer.name + ", it is your turn!");
            if (lastBetter == null) {
                System.out.println(activePlayer.name + ", what will your bet be?\nEnter a die value: \n");
                int dieValue = scanner.nextInt();
                System.out.println("Enter a quantity for that die: \n");
                int quantity = scanner.nextInt();
                bet(dieValue, quantity);
                System.out.println(activePlayer.name + " says there are " + betNumberOfDice + " " + betDieValue + "'s" +
                        " on the table.\nNext turn!");
            } else {
                System.out.println(activePlayer.name + ", would you like to \n(1) make a new bet, or \n(2) call " + lastBetter.name + " a liar?");
                int choice = scanner.nextInt();

                switch (choice) {
                    //TODO Refine this into a valid/invalid betting system
                    case 1:
                        System.out.println(activePlayer.name + ", what will your bet be?\nEnter a die value: \n");
                        int dieValue = scanner.nextInt();
                        System.out.println("Enter a quantity for that die: \n");
                        int quantity = scanner.nextInt();
                        bet(dieValue, quantity);
                        System.out.println(activePlayer.name + " says there are " + betNumberOfDice + " " + betDieValue + "'s" +
                                " on the table.\nNext turn!");
                        break;

                    case 2:
                        System.out.println(activePlayer.name + " claims " + lastBetter.name + " is lying...");
                        callLiar();
                        //Call outer method here
                }
            }
        }
    }

    public void bet(int dieValue, int numberOfDice) {
        lastBetter = activePlayer;
        betDieValue = dieValue;
        betNumberOfDice = numberOfDice;
    }

    public void callLiar() {
        if (table.tableDice.containsKey(betDieValue) && table.tableDice.get(betDieValue) <= betNumberOfDice) {
            System.out.println(lastBetter.name + " told the truth!\n" + activePlayer.name + " loses a die!");
            activePlayer.cup.removeDie();
        } else {
            System.out.println(lastBetter.name + " is a liar!\n" + lastBetter.name + " loses a die!");
            lastBetter.cup.removeDie();;
        }
    }

}
