package com.poker.cards.service;

import com.poker.cards.domain.PokerCardType;
import com.poker.cards.domain.PokerCardValue;
import com.template.cards.AbstractCard;
import com.template.cards.service.CardFactoryInterface;

import java.util.List;

public interface PokerCardFactory<T extends AbstractCard<PokerCardType, PokerCardValue>> extends CardFactoryInterface<T> {
    List<T> create24CardDeck(); // 9 through Ace of each suit
    List<T> create36CardDeck(); // 6 through Ace of each suit
    List<T> create52CardDeck(); // 2 through Ace of each suit
}
