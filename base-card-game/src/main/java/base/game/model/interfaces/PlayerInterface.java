package base.game.model.interfaces;

import base.game.domain.AbstractDeck;
import com.template.cards.AbstractCard;
import base.game.domain.Hand;

public interface PlayerInterface <Card extends AbstractCard>{

    void playCard(Card card);
    void getHandFromDeck(AbstractDeck<Card> deck, int cardsInHandNumber);
    String getName();
    Hand<Card> getHand();

}
