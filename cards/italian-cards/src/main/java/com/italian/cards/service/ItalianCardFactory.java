package com.italian.cards.service;

import com.italian.cards.ItalianCard;
import com.italian.cards.domain.CardType;
import com.italian.cards.domain.CardValue;
import com.template.cards.service.CardFactoryInterface;

import java.util.ArrayList;
import java.util.List;

public class ItalianCardFactory implements CardFactoryInterface<ItalianCard> {

    public static final int ITALIAN_DECK_CARD_NUMBER = 40;

    @Override
    public List<ItalianCard> createAllCards() {
        var res = new ArrayList<ItalianCard>();

        var cardTypes = CardType.values();
        var cardValues = CardValue.values();

        for (var type : cardTypes) {
            for (var value : cardValues) {
                res.add(new ItalianCard(type, value));
            }
        }

        return res;
    }
}
