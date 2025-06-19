package service.model.interfaces;

import com.AbstractCard;
import domain.Deck;
import domain.Hand;

public interface PlayerInterface <Card extends AbstractCard>{

    void playCard(Card card);
    void getHandFromDeck(Deck<Card> deck, int cardsInHandNumber);
    String getName();
    Hand<Card> getHand();

}
