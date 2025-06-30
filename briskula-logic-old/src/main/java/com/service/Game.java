package com.service;

import com.domain.Card;
import com.domain.CardsInPlay;
import com.domain.Deck;

import java.util.*;

public class Game {
    private List<Player> players;
    private Deck deck;
    private Card trumpCard;
    private final int numberOfPlayers;
    private final int cardsNum;


    public Game(int numberOfPlayers, int cardsNum) {
        this.numberOfPlayers = numberOfPlayers;
        this.cardsNum = cardsNum;
    }

    public final void start() {
        deck = createDeck();
        cutDeckDown();
        players = createPlayers();
        setPlayerNames();
        shufflePlayers();
        trumpCard = generateTrumpCard();
        pregameRoutine();

        while (!deck.isEmpty()) {
            var cip = new CardsInPlay(deck, getNumberOfPlayers());
            gameCycle(cip);
            var winner = determineRoundWinner(cip);
            addPointsToWinner(winner, cip);
            changePlayersOrder(winner);
            drawCards();
            endOfRoundRoutine(winner, cip);
        }

        var winner = determineWinner();
        endOfGame(winner);

    }

    public void addPointsToWinner(Player winner, CardsInPlay cip) {
        winner.addPoints(cip.getNumberOfPoints());
    }

    public void endOfRoundRoutine(Player winner, CardsInPlay cip) {}

    public void pregameRoutine() {}

    public void shufflePlayers() {
        Collections.shuffle(players);
    }

    public void drawCards() {
        if (deck.getNumberOfCardsLeft() >= getNumberOfPlayers() - 1) {
            for (var player : players) {
                player.getHand().addCard(
                        deck.drawCard()
                );
            }
        }
    }

    public void changePlayersOrder(Player winner) {
        var id = players.indexOf(winner);
        Collections.rotate(players, -id);
    }

    public Player determineRoundWinner(CardsInPlay cip) {
        return cip.determineWinner(trumpCard.type());
    }

    public void setPlayerNames() {
        var i = 0;
        for (var player : players) {
            player.setName(String.format("Player %d", i + 1));
            i++;
        }
    }

    public Player determineWinner() {
        var winner = players.getFirst();

        for (var player : players) {
            winner = winner.getPoints() > player.getPoints() ? winner : player;
        }

        return winner;
    }

    public void gameCycle(CardsInPlay cip) {
        for (var player : players) {
            player.playCard(cip);
        }
    }

    public void endOfGame(Player winner) {}

    public void cutDeckDown() {
        var howManyToRemove = getDeck().getNumberOfCardsLeft() % getNumberOfPlayers();

        for (int i = 0; i < howManyToRemove; i++) {
            deck.drawCard();
        }
    }

    public Deck createDeck() {
        return new Deck();
    }

    public Card generateTrumpCard() {
        return deck.getTrumpCard();
    }

    public List<Player> createPlayers() {
        var res = new ArrayList<Player>(getNumberOfPlayers());
        for (int i = 0; i < getNumberOfPlayers(); i++) {
            res.add(new Player(deck.createHand(getCardsNum()), i));
        }
        return res;
    }


    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public int getCardsNum() {
        return cardsNum;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Deck getDeck() {
        return deck;
    }

    public Card getTrumpCard() {
        return trumpCard;
    }
}
