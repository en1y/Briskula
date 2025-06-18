package com.service;

import com.Card;

import java.util.List;

public interface PokerCardFactory<T extends Card> extends CardFactoryInterface<Card> {
    List<T> create24CardDeck(); // 9 through Ace of each suit
    List<T> create36CardDeck(); // 6 through Ace of each suit
    List<T> create52CardDeck(); // 2 through Ace of each suit
}
