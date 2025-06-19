package domain.interfaces;

import com.AbstractCard;

import java.util.List;

public interface HandInterface<Card extends AbstractCard> {

    boolean isEmpty();
    void drawCard(Card card);
    void addCard(Card card);
    void addCards(List<Card> cards);
    int getHandCapacity();
    int getCardsNum();
    List<Card> getCards();

}
