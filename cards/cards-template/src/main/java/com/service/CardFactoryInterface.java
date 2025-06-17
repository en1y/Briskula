package com.service;

import com.AbstractCard;

import java.util.List;

public interface CardFactoryInterface<T extends AbstractCard> {
    List<T> createAllCards ();
}
