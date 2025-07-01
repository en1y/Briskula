package com.template.cards.domain;

import base.game.domain.AbstractDeck;
import com.italian.cards.ItalianCard;
import com.italian.cards.service.ItalianCardFactory;

import java.util.Collections;
import java.util.List;

public class Deck extends AbstractDeck<ItalianCard> {
    public Deck(int cardsNum) {
        super(cardsNum);
    }

    @Override
    public List<ItalianCard> createCards(int cardsNum) {
        return new ItalianCardFactory().createAllCards();
    }

    @Override
    public void shuffleCards(List<ItalianCard> cards) {
        Collections.shuffle(cards);
    }
}
