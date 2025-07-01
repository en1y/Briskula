package base.game.model;

import base.game.domain.AbstractDeck;
import com.template.cards.AbstractCard;
import base.game.domain.Hand;
import base.game.model.interfaces.PlayerInterface;

public abstract class AbstractPlayer<Card extends AbstractCard> implements PlayerInterface<Card> {

    private Hand<Card> hand;
    private final String name;

    public AbstractPlayer(String name) {
        this.name = name;
    }

    @Override
    public void getHandFromDeck(AbstractDeck<Card> deck, int cardsInHandNumber) {
        hand = deck.createHand(cardsInHandNumber);
    }

    @Override
    public final void playCard(Card card) {
        hand.drawCard(card);
    }

    public String getName() {
        return name;
    }

    public Hand<Card> getHand() {
        return hand;
    }

    @Override
    public String toString() {
        return "Player: " + name;
    }
}
