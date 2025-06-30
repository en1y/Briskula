package com.service;

import com.domain.Card;
import com.domain.CardsInPlay;
import com.domain.Hand;

public class Player {
    private Hand hand;
    private String name;
    private int points;
    private final int id;

    public Player(Hand hand, int id) {
        this.hand = hand;
        this.id = id;
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

    public void removeCardFromHand (Card card) {
        hand.removeCard(card);
    }

    public Card getCardToPlay() {
        return hand.getCard(0);
    }

    public int getId() {
        return id;
    }
}
