package service;

import domain.Card;
import domain.CardsInPlay;
import domain.Deck;

import java.util.*;

public class Game {
    private Queue<Player> players;
    private Deck deck;
    private Card trumpCard;
    private final int numberOfPlayers;
    private final int cardsNum;


    public Game(int numberOfPlayers, int cardsNum) {
        this.numberOfPlayers = numberOfPlayers;
        this.cardsNum = cardsNum;

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

    public void start() {
        deck = createDeck();
        cutDeckDown();
        players = createPlayers();
        setPlayerNames();
        trumpCard = generateTrumpCard();
        
        while (!deck.isEmpty()) {
            var cip = new CardsInPlay(deck, getNumberOfPlayers());
            gameCycle(cip);
            var winner = determineRoundWinner(cip);
            changePlayersOrder(winner);
            drawCards();
        }

        var winner = determineWinner();
        endOfGame(winner);

    }

    public void drawCards() {
        for (var player : players) {
            player.getHand().addCard(
                    deck.drawCard()
            );
        }
    }

    public void changePlayersOrder(Player winner) {
        while (!players.peek().equals(winner)) {
            players.add(players.poll());
        }
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
        var winner = players.peek();

        for (var player : players) {
            winner = winner.getPoints() > player.getPoints() ? winner : player;
        }

        return winner;
    }

    public void gameCycle(CardsInPlay cip) {
        for (var player : players) {

        }
    }

    public void endOfGame(Player winner) {}

    public void cutDeckDown() {
        var howManyToRemove = getDeck().getNumberOfCardsLeft() % getCardsNum();

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

    public Queue<Player> createPlayers() {
        var res = new ArrayList<Player>(getNumberOfPlayers());
        for (int i = 0; i < getNumberOfPlayers(); i++) {
            res.add(new Player(deck.createHand(getCardsNum())));
        }
        Collections.shuffle(res);
        return new ArrayDeque<>(res);
    }


    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public int getCardsNum() {
        return cardsNum;
    }

    public Queue<Player> getPlayers() {
        return players;
    }

    public Deck getDeck() {
        return deck;
    }

    public Card getTrumpCard() {
        return trumpCard;
    }
}
