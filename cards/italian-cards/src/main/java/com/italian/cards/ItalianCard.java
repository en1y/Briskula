package com.italian.cards;

import com.template.cards.AbstractCard;
import com.italian.cards.domain.CardType;
import com.italian.cards.domain.CardValue;

import java.util.Locale;

public class ItalianCard extends AbstractCard<CardType, CardValue> {

    public ItalianCard(CardType type, CardValue value) {
        super(type, value);
    }

    public static void setLocale (Locale locale) {
        CardType.useResourceBundle(locale);
        CardValue.useResourceBundle(locale);
    }

    @Override
    public String toString() {
        return (
                new StringBuilder()
                        .append(getValue().getName())
                        .append(" ")
                        .append(getType().getName()).toString()
                );
    }
}
