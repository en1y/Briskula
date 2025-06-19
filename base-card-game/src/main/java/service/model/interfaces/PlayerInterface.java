package service.model.interfaces;

import com.AbstractCard;
import domain.Deck;

public interface PlayerInterface <Card extends AbstractCard>{

    void playCard(Card card);
    void getHandFromDeck(Deck<Card> deck, int cardsInHandNumber);

}
