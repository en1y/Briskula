package com.template.cards;

import com.template.cards.domain.CardTypeInterface;
import com.template.cards.domain.CardValueInterface;

public abstract class AbstractCard<CardType extends CardTypeInterface, CardValue extends CardValueInterface> implements Comparable<AbstractCard> {
    private final CardType type;
    private final CardValue value;

    public AbstractCard(CardType type, CardValue value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public int compareTo(AbstractCard o) {
        throw new UnsupportedOperationException("This method has to be implemented");
    }

    public CardType getType() {
        return type;
    }

    public CardValue getValue() {
        return value;
    }
}
