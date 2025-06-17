package com;

import com.domain.CardTypeInterface;
import com.domain.CardValueInterface;

public abstract class AbstractCard implements Comparable<AbstractCard> {
    private final CardTypeInterface type;
    private final CardValueInterface value;

    public AbstractCard(CardTypeInterface type, CardValueInterface value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public int compareTo(AbstractCard o) {
        throw new UnsupportedOperationException("This method has to be implemented");
    }

    public CardTypeInterface getType() {
        return type;
    }

    public CardValueInterface getValue() {
        return value;
    }
}
