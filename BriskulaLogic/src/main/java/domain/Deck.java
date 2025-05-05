package domain;

import service.CardFactory;

import java.util.Collections;
import java.util.List;

public class Deck {

    private final List<Card> cards;

    public Deck() {
        cards = CardFactory.createDeck();
        Collections.shuffle(cards);
    }

    public Hand createHand(int cardsNum) {
        var hand = new Hand();

        for (int i = 0; i < cardsNum; i++) {
            hand.addCard(
                    cards.removeFirst()
            );
        }

        return hand;
    }

}
