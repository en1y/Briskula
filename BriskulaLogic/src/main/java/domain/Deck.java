package domain;

import service.CardFactory;

import java.util.List;

public class Deck {
    private List<Card> cards;

    public Deck() {
        cards = CardFactory.createDeck();
    }

}
