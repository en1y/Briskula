package domain;

import com.AbstractCard;
import domain.exceptions.HandException;
import domain.interfaces.HandInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Hand<Card extends AbstractCard> implements HandInterface<Card> {

    private final List<Card> cards;
    private final int capacity;
    private int cardsNum;

    public Hand(int handCapacity) {
        if (handCapacity <= 0) {
            throw new HandException("Hand capacity: %d, can not be less than 1", handCapacity);
        }

        cards = new ArrayList<>(handCapacity);
        capacity = handCapacity;
        cardsNum = 0;
    }

    private void validateCardsNumber(int newCardsNum) {
        if (newCardsNum >= capacity) {
            throw new HandException("Can not add card/s because the new cards in hand num: %d is higher than the capacity: %d", newCardsNum +1, capacity);
        }
    }

    @Override
    public final void addCard(Card card) {
        Objects.requireNonNull(card);

        validateCardsNumber(cardsNum+1);
        cards.add(card);
        cardsNum++;
    }

    @Override
    public final void addCards(List<Card> cards) {
        Objects.requireNonNull(cards);

        var cardsSize = cards.size();
        validateCardsNumber(cardsNum+cardsSize);
        cardsNum += cardsSize;
        this.cards.addAll(cards);
    }

    @Override
    public final void drawCard(Card card) {
        Objects.requireNonNull(card);

        cards.remove(card);
        cardsNum--;
    }

    @Override
    public final int getCardsNum() {
        return cardsNum;
    }

    @Override
    public final int getHandCapacity() {
        return capacity;
    }

    @Override
    public final boolean isEmpty() {
        return cardsNum == 0;
    }

    @Override
    public final List<Card> getCards() {
        return new ArrayList<>(cards);
    }
}
