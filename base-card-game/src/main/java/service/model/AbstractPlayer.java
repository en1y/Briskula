package service.model;

import com.AbstractCard;
import domain.Deck;
import domain.Hand;
import service.model.interfaces.PlayerInterface;

public abstract class AbstractPlayer<Card extends AbstractCard> implements PlayerInterface<Card> {

    private Hand<Card> hand;
    private final String name;

    public AbstractPlayer(String name) {
        this.name = name;
    }

    @Override
    public void getHandFromDeck(Deck<Card> deck, int cardsInHandNumber) {
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
}
