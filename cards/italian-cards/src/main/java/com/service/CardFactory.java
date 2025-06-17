package com.service;

import com.Card;
import com.domain.CardType;
import com.domain.CardValue;

import java.util.ArrayList;
import java.util.List;

public class CardFactory implements CardFactoryInterface<Card> {
    @Override
    public List<Card> createAllCards() {
        var res = new ArrayList<Card>();

        var cardTypes = CardType.values();
        var cardValues = CardValue.values();

        for (var type : cardTypes) {
            for (var value : cardValues) {
                res.add(new Card(type, value));
            }
        }

        return res;
    }
}
