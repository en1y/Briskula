package domain;

import service.Player;

import java.util.ArrayList;
import java.util.List;

public class CardsInPlay {
    private final List<Card> cards;
    private final List<Player> players;
    private final Deck deck;
    private final int numberOfCards;

    public CardsInPlay(Deck deck, int numberOfCards) {
        this.deck = deck;
        this.cards = new ArrayList<>();
        this.players = new ArrayList<>();
        this.numberOfCards = numberOfCards;
    }

    public void play(Card card, Player player) {
        if (cards.size() < numberOfCards) {
            deck.removeCard(card);
            player.removeCardFromHand(card);
            players.add(player);
            cards.add(card);
        }
        else throw new CardsInPlayException("Max number of cards in play reached: " + cards.size());
    }

    public Player determineWinner(CardType trumpCardType) {
        var size = cards.size();
        if (size == numberOfCards){
            var strongestCard = cards.getFirst();
            for (int i = 1; i < size; i++) {
                strongestCard = strongestCard.compareTo(cards.get(i), trumpCardType);
            }
            return players.get(cards.indexOf(strongestCard));
        }
        throw new CardsInPlayException("Not enough cards to determine winner");
    }

    public int getNumberOfPoints() {
        var res = 0;
        for (var card : cards) {
            res += card.value().getValue();
        }
        return res;
    }

    @Override
    public String toString() {
        var res = new StringBuilder();

        for (int i = 0; i < cards.size(); i++) {
            res.append("   ")
                    .append(i+1)
                    .append(". ")
                    .append(cards.get(i).toString())
                    .append(System.lineSeparator());
        }

        if (res.isEmpty()) res.append("Karte još nisu bile odigrane");

        return res.toString();
    }

    public String toCroatianString() {
        var res = new StringBuilder();

        for (int i = 0; i < cards.size(); i++) {
            res.append("   ")
                    .append(i+1)
                    .append(". ")
                    .append(cards.get(i).toCroatianString())
                    .append(System.lineSeparator());
        }

        if (res.isEmpty()) res.append("Karte još nisu bile odigrane");

        return res.toString();
    }

}

class CardsInPlayException extends RuntimeException {
    public CardsInPlayException(String message) {
        super(message);
    }
}
