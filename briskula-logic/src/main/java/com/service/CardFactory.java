package com.service;

import com.domain.Card;
import com.domain.CardType;
import com.domain.CardValue;

import java.util.ArrayList;
import java.util.List;

public class CardFactory {

    public static Card createCard(CardType type, CardValue value) {
        return new Card(type, value);
    }

    public static List<Card> createDeck() {
        var res = new ArrayList<Card>();

        var cardTypes = CardType.values();
        var cardValues = CardValue.values();

        for (var cardType : cardTypes) {
            for (var cardValue : cardValues) {
                res.add(createCard(cardType, cardValue));
            }
        }

        return res;
    }
}
