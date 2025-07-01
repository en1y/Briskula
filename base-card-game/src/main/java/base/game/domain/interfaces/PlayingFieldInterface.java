package base.game.domain.interfaces;

import com.template.cards.AbstractCard;
import base.game.model.AbstractPlayer;

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

    Card getCardPlayedBy(Player player);
}
