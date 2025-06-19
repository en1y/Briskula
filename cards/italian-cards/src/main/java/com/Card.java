package com;

import com.domain.CardType;
import com.domain.CardValue;

import java.util.Locale;

public class Card extends AbstractCard {

    public Card(CardType type, CardValue value) {
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
                        .append(getType().getName()).toString()
                );
    }
}
