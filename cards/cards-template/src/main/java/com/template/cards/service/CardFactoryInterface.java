package com.template.cards.service;

import com.template.cards.AbstractCard;

import java.util.List;

public interface CardFactoryInterface<T extends AbstractCard> {
    List<T> createAllCards ();
}
