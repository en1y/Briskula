package service;

import domain.Card;
import domain.CardsInPlay;
import domain.Hand;

public class Player {
    private Hand hand;
    private String name;
    private int points;

    public Player(Hand hand) {
        this.hand = hand;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addPoints (int points) {
        this.points += points;
    }

    public int getPoints() {
        return points;
    }

    public void playCard (CardsInPlay cip){
        cip.play(getCardToPlay(), this);
    }

    public Card getCardToPlay() {
        return hand.getCard(0);
    }
}
