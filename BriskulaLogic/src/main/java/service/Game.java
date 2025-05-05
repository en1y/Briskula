package service;

import domain.Deck;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class Game {
    private Queue<Player> players;
    private Deck deck;
    private int currentPlayer = 0;
    private int numberOfPlayers;

    public Game(int numberOfPlayers, int cardsNum) {
        this.numberOfPlayers = numberOfPlayers;

        players = new ArrayDeque<>(numberOfPlayers);

        deck = new Deck();
        for (int i = 0; i < numberOfPlayers; i++) {
            players.add(
                    new Player(
                            deck.createHand(cardsNum)
                    )
            );
        }
    }
}
