package com.company;

public class Main {

    /*
    Plan of Attack:

    Classes   Fields/Methods

    Player    (String name, int numberOfDice, Cup cup);
    Table     (Map<Die, Integer> tableDice);
    Game      (int numberOfPlayers, List<Player> players, Player activePlayer, Player lastBetter,
               addPlayer(Player, player), bet(Die dieValue, int quantity), callLie(Player lastBetter));
    Cup       (List<Die> dicePool, addDice(int numOfDice), removeDie(), rollDice());
    Die       (int numberOfSides, int faceUpValue, roll(), getFaceUpValue());

    Required Features

        Minimum 2 players
        dice are hidden
        Valid claims are made
        Player can accuse previous player of lying
        All dice are counted and player bid is evaluated correctly
        Round loser looses one die
        Player with no dice is removed from game
        Last player standing is winner.



     */

    public static void main(String[] args) {

    }
}
