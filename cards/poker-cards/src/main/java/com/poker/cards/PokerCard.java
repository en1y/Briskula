package com.poker.cards;

import com.poker.cards.domain.PokerCardType;
import com.poker.cards.domain.PokerCardValue;
import com.template.cards.AbstractCard;

public class PokerCard extends AbstractCard<PokerCardType, PokerCardValue> {
    public PokerCard(PokerCardType type, PokerCardValue value) {
        super(type, value);
    }
}
