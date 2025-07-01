package com.template.cards.domain;

import com.italian.cards.ItalianCard;
import com.italian.cards.domain.CardValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BriskulaCardValue {

    private static Map<CardValue, Integer> cardValueMap = null;

    public BriskulaCardValue() {
        if (cardValueMap == null) {
            cardValueMap = new HashMap<>(
                Map.of(
                        CardValue.ACE, 11,
                        CardValue.THREE, 10,
                        CardValue.JACK, 2,
                        CardValue.KNIGHT, 3,
                        CardValue.KING, 4
                )
            );
        }

    }

    public int getValue(ItalianCard card){
        var value = card.getValue();
        return cardValueMap.getOrDefault(value, 0);
    }

}
