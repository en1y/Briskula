package com.domain;

import com.service.CardFactory;

import java.util.Collections;
import java.util.List;

public class Deck {

    private final List<Card> cards;

    public Deck() {
        cards = CardFactory.createDeck();
        Collections.shuffle(cards);
    }

    public Hand createHand(int cardsNum) {
        var hand = new Hand();

        for (int i = 0; i < cardsNum; i++) {
            hand.addCard(
                    cards.removeFirst()
            );
        }
        return hand;
    }

    public Card drawCard() {
        return cards.removeFirst();
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public Card getTrumpCard() {
        return cards.getLast();
    }

    public int getNumberOfCardsLeft() {
        return cards.size();
    }

}
