package com.poker.cards.service;

import com.poker.cards.PokerCard;
import com.poker.cards.domain.PokerCardType;
import com.poker.cards.domain.PokerCardValue;

import java.util.ArrayList;
import java.util.List;

public class CardFactory implements PokerCardFactory<PokerCard> {
    @Override
    public List<PokerCard> create52CardDeck() {
        return createXToAceDeck(2);
    }

    @Override
    public List<PokerCard> create36CardDeck() {
        return createXToAceDeck(6);
    }

    @Override
    public List<PokerCard> create24CardDeck() {
        return createXToAceDeck(9);
    }

    private List<PokerCard> createXToAceDeck(int startNumber) {
        var res = new ArrayList<PokerCard>();

        var cardTypes = PokerCardType.values();
        var cardValues = PokerCardValue.values();

        for (var type : cardTypes) {
            for (var value : cardValues) {
                if (value.getNumber() >= startNumber) {
                    res.add(new PokerCard(type, value));
                }
            }
        }

        return res;
    }

    @Override
    @Deprecated
    public List<PokerCard> createAllCards() {
        return create52CardDeck();
    }
}
