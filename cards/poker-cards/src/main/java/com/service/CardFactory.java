package com.service;

import com.Card;
import com.domain.CardType;
import com.domain.CardValue;

import java.util.ArrayList;
import java.util.List;

public class CardFactory implements PokerCardFactory<Card> {
    @Override
    public List<Card> create52CardDeck() {
        return createXToAceDeck(2);
    }

    @Override
    public List<Card> create36CardDeck() {
        return createXToAceDeck(6);
    }

    @Override
    public List<Card> create24CardDeck() {
        return createXToAceDeck(9);
    }

    private List<Card> createXToAceDeck(int startNumber) {
        var res = new ArrayList<Card>();

        var cardTypes = CardType.values();
        var cardValues = CardValue.values();

        for (var type : cardTypes) {
            for (var value : cardValues) {
                if (value.getNumber() >= startNumber) {
                    res.add(new Card(type, value));
                }
            }
        }

        return res;
    }

    @Override
    @Deprecated
    public List<Card> createAllCards() {
        return create52CardDeck();
    }
}
