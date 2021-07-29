package com.company;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

//TODO Fix end of game condition and add validation of bids

public class Game {
    public int numberOfPlayers;
    public int betDieValue;
    public int betNumberOfDice;
    public boolean gameIsActive = true;
    public Player activePlayer;
    public Player lastBetter;
    public Table table = new Table();
    private final Scanner scanner = new Scanner(System.in);
    public ArrayList<Player> players = new ArrayList<>();


    public Game() {
        int playerNumber = 0;
        int diceNumber = 0;

        do {
            System.out.print("How many players?\nMinimum 2:\n");
            playerNumber = scanner.nextInt();
            numberOfPlayers = playerNumber;
            scanner.nextLine();
            System.out.print("How many Dice will you play with?\nMinimum 3:\n");
            diceNumber = scanner.nextInt();
        } while (playerNumber < 2 && diceNumber < 3);



        while (players.size() < playerNumber) {
            scanner.nextLine();
            String name = createPlayer();
            if (name.equals("")) {
                System.out.println("Invalid name entry, try again.");
                createPlayer();
            }

            players.add(new Player(name, diceNumber));
            System.out.println(name + " has been added to the game");

        }

        while (gameIsActive) {
            checkGameStatus();
            round();
        }

        Player winner = null;

        for (Player player : players) {
            if (!player.isOut) {
                winner = player;
            }
        }

        System.out.println(winner.name + " is the winner!\nPlay again? (Y)es\n(N)o\n");

        String newGame = scanner.nextLine();

        switch (newGame.toLowerCase(Locale.ROOT)) {
            case "y" :
                System.out.println("New Game initiated!");
                new Game();
                break;

            case "n" :
                System.out.println("Thank you for playing!");
                System.exit(0);
        }


    }

    private void addPlayer(Player player) {
        if (players.size() < numberOfPlayers) {
            players.add(player);
        }
    }


    //TODO Finish round() method
    public void round() {
        checkGameStatus();
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


        lastBetter = null;


        for (Player player : players) {
            if (!player.isOut) {
                activePlayer = player;
                System.out.println(activePlayer.name + ", it is your turn!");
                System.out.println(activePlayer.name + "'s hand of dice:");
                for (Die die : activePlayer.cup.dice) {
                    System.out.println(die.faceUpValue);
                }
                if (lastBetter == null) {
                    System.out.println(activePlayer.name + ", what will your bet be?\nEnter a die value: \n");
                    int dieValue = scanner.nextInt();
                    System.out.println("Enter a quantity for that die: \n");
                    int quantity = scanner.nextInt();
                    bet(dieValue, quantity);
                    System.out.println(activePlayer.name + " says there are " + betNumberOfDice + " " + betDieValue + "'s" +
                            " on the table.\nNext turn!");
                } else {
                    System.out.println(activePlayer.name + ", would you like to \n(1) make a new bet, or \n(2) call " + lastBetter.name + " a liar?\nLast bet: " + betNumberOfDice + " die/dice of " + betDieValue + "'s");
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
                            round();
                    }
                }
            }
        }
        checkGameStatus();

    }

    private String createPlayer() {
        System.out.println("Enter your name:");
        String name = scanner.nextLine();
        return name;

    }

    public void bet(int dieValue, int numberOfDice) {
        lastBetter = activePlayer;
        betDieValue = dieValue;
        betNumberOfDice = numberOfDice;
    }

    public void callLiar() {
        if (table.tableDice.containsKey(betDieValue) && table.tableDice.get(betDieValue) >= betNumberOfDice) {
            System.out.println(showTableDice());
            System.out.println(lastBetter.name + " told the truth!\n" + activePlayer.name + " loses a die!");
            activePlayer.cup.removeDie();
            activePlayer.checkCondition();
            checkGameStatus();
            if (activePlayer.isOut) {
                System.out.println(activePlayer.name + " has been eliminated!");
                checkGameStatus();
            }
        } else {
            System.out.println(showTableDice());
            System.out.println(lastBetter.name + " is a liar!\n" + lastBetter.name + " loses a die!");
            lastBetter.cup.removeDie();
            lastBetter.checkCondition();
            checkGameStatus();
            if (lastBetter.isOut) {
                System.out.println(lastBetter.name + " has been eliminated!");
                checkGameStatus();
            }
        }
    }


    private void checkGameStatus() {
        int outPlayers = 0;

        for (Player player : players) {
            if (player.isOut) {
                outPlayers++;
            }
        }

        System.out.println("Out Players: " + outPlayers + "\nNumber of Players: " + numberOfPlayers);
        gameIsActive = numberOfPlayers - 1 < outPlayers;
        System.out.println("Game is Active:" + gameIsActive);
    }

    private String showTableDice() {
        String tableDice = "Dice at table:\n";

        for (int die : table.tableDice.keySet()) {
            tableDice += table.tableDice.get(die) + " count of " + die + "'s, ";
        }

        return tableDice;
    }

}
