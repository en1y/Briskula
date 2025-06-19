package service.model;

import com.AbstractCard;
import domain.Hand;
import service.model.interfaces.PlayerInterface;

public abstract class AbstractPlayer<Card extends AbstractCard> implements PlayerInterface<Card> {

    private Hand<Card> hand;

    @Override
    public void playCard(Card card) {
        hand.drawCard(card);
    }
}
