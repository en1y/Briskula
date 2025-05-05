package domain;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private final List<Card> cards;

    public Hand() {
        cards = new ArrayList<Card>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    @Override
    public String toString() {
        var res = new StringBuilder();

        res.append("Your hand: ").append(System.lineSeparator());

        for (var card : cards) {
            res.append("   ").append(card.toString()).append(System.lineSeparator())
                    .append("   ").append(card.toString()).append(System.lineSeparator())
                    .append("   ").append(card.toString()).append(System.lineSeparator());
        }

        return res.toString();
    }

    public String toCroatianString() {
        var res = new StringBuilder();

        res.append("Tvoja ruka: ").append(System.lineSeparator());

        for (var card : cards) {
            res.append("   ").append(card.toCroatianString()).append(System.lineSeparator())
                    .append("   ").append(card.toCroatianString()).append(System.lineSeparator())
                    .append("   ").append(card.toCroatianString()).append(System.lineSeparator());
        }

        return res.toString();
    }
}
