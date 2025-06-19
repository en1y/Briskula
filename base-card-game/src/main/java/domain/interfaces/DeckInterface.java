package domain.interfaces;

import com.AbstractCard;
import domain.Hand;

import java.util.List;

public interface DeckInterface <Card extends AbstractCard> {

    /**
     * Should not implement shuffling. Just the deck creation.
     * @return all the cards that are to be played with
     */
    List<Card> createCards(int cardsNum);

    /**
     * @param cards the cards to be shuffled
     */
    void shuffleCards(List<Card> cards);
    /**
     * @param cardsNum
     * @return a Hand instance with <code>cardsNum</code> cards
     * from the deck that are automatically removed
     */
    Hand<Card> createHand(int cardsNum);
    AbstractCard drawCard();
    List<Card> drawXCards(int cardsNum);
    boolean isEmpty();
    int getSize();

}
