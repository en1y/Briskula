package domain;

import com.AbstractCard;
import domain.interfaces.PlayingFieldInterface;
import service.model.AbstractPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlayingField
        <Card extends AbstractCard, Player extends AbstractPlayer<Card>>
        implements PlayingFieldInterface<Card, Player> {

    private final List<Card> cards;
    private final List<Player> players;

    public PlayingField() {
        cards = new ArrayList<>();
        players = new ArrayList<>();
    }

    @Override
    public void play(Card card, Player player) {
        Objects.requireNonNull(card);
        Objects.requireNonNull(player);

        cards.add(card);
        player.playCard(card);
        players.add(player);

    }

    @Override
    public Player determineRoundWinner() {
        throw new UnsupportedOperationException("""
                Determining round winner is not implemented yet.
                To implement it override the PlayingField.determineRoundWinner() method""");
    }


    @Override
    public List<Card> getPlayedCards() {
        return new ArrayList<>(cards);
    }

    @Override
    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }
}
