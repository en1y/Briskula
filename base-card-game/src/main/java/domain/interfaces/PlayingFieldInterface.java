package domain.interfaces;

import com.AbstractCard;
import service.model.AbstractPlayer;

import java.util.List;

public interface PlayingFieldInterface<Card extends AbstractCard, Player extends AbstractPlayer> {

    void play(Card card, Player player);
    Player determineRoundWinner();

    /**
     * @return cards that were played in the order that they were played
     */
    List<Card> getPlayedCards();

    /**
     * @return players that were played in the order that they were played
     */
    List<Player> getPlayers();
}
