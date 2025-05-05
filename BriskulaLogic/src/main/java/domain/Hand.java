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

    public Card getCard(int index) {
        return cards.get(index);
    }

    @Override
    public String toString() {
        if (!isEmpty()) {
            var res = new StringBuilder();

            res.append("Your hand: ").append(System.lineSeparator());

            var len = cards.size();

            for (int i = 0; i < len; i++) {
                res.append("   ")
                        .append(i+1)
                        .append(". ")
                        .append(cards.get(i).toString())
                        .append(System.lineSeparator());
            }

            return res.toString();
        } else {
            return "Your hand is empty";
        }
    }

    public String toCroatianString() {
        if (!isEmpty()){
            var res = new StringBuilder();

            res.append("Tvoja ruka: ").append(System.lineSeparator());
            var len = cards.size();

            for (int i = 0; i < len; i++) {
                res.append("   ")
                        .append(i+1)
                        .append(". ")
                        .append(cards.get(i).toCroatianString())
                        .append(System.lineSeparator());
            }

            return res.toString();

        } else {
            return "Ruke su ti prazne";
        }
    }
    
    public boolean isEmpty() {
        return cards.isEmpty();
    }
    
    public void removeCard(Card card) {
        cards.remove(card);
    }
    
}

class EmptyHandException extends RuntimeException {}
