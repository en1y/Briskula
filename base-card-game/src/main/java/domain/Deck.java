package domain;

import com.AbstractCard;
import domain.exceptions.DeckException;
import domain.interfaces.DeckInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Deck<Card extends AbstractCard> implements DeckInterface<Card> {

    private final List<Card> cards;
    private int size;

    /**
     * First calls the Deck.createDeck() method to initialize the deck.<br>
     * Second calls the Deck.
     * You should implement the Locale for the cards you are using.
     */
    public Deck(int cardsNum) {
        cards = createCards(cardsNum);
        size = cardsNum;
        checkCardsLength();
        shuffleCards(cards);
        checkCardsLength();
    }

    private void checkCardsLength() {
        if (cards.size() != size) {
            throw new DeckException("Deck size should be %d. But instead it is %d", size, cards.size());
        }
    }

    @Override
    public List<Card> createCards(int cardsNum) {
        throw new UnsupportedOperationException("""
                Creating cards is not implemented yet.
                To implement it override the Deck.createCards() method""");
    }

    @Override
    public void shuffleCards(List<Card> cards) {
        throw new UnsupportedOperationException("""
                Shuffling cards is not implemented yet.
                To implement it override the Deck.shuffleCards() method""");
    }

    @Override
    public final int getSize() {
        return size;
    }

    @Override
    public final boolean isEmpty() {
        return size == 0;
    }

    @Override
    public final Card drawCard() {
        size--;
        if (size < 0) {
            throw new DeckException("Deck size should be >= 0 but instead it is %d", size);
        }
        return cards.removeFirst();
    }

    @Override
    public final List<Card> drawXCards(int cardsNum) {
        checkForSufficientNumberOfCards(cardsNum, "Can not draw %d cards since there are only %d cards left in the deck");

        var res = new ArrayList<Card>(cardsNum);
        for (int i = 0; i < cardsNum; i++) {
            res.add(drawCard());
        }

        return res;
    }

    @Override
    public final Hand<Card> createHand(int cardsNum) {
        checkForSufficientNumberOfCards(cardsNum, "Can not create a hand with %d cards since there are only %d cards left in the deck");

        var res = new Hand<Card>(cardsNum);
        res.addCards(
                drawXCards(cardsNum)
        );
        return res;
    }

    private void checkForSufficientNumberOfCards(int cardsNum, String errorMessageToBeFormatted) {
        if (size < cardsNum) {
            throw new DeckException(errorMessageToBeFormatted, cardsNum, size);
        }
    }

    @Override
    public String toString() {
        var res = new StringBuilder();
        for (Card card : cards) {
            res.append(card.toString()).append("\n");
        }
        return res.toString();
    }
}
