package base.game.model.interfaces;

import com.template.cards.AbstractCard;
import base.game.domain.AbstractDeck;
import base.game.domain.PlayingField;
import base.game.model.AbstractPlayer;

import java.util.List;

public interface GameInterface <Card extends AbstractCard, Player extends AbstractPlayer<Card>, Deck extends AbstractDeck<Card>> {

    void preGameCreateCheck(int numberOfPlayers, int cardsNum);

    /**
     * The main method that starts the game <br>
     * Cannot be overridden<br>
     */
    void start();

    void restart();

    void preDeckSetUp();
    /**
     * Cannot be overridden
     */
    void deckSetUp();
    Deck createDeck(int cardsNum);
    List<Card> removeNotNeededCards(Deck deck);
    void postDeckSetUp(List<Card> removedCards);

    void prePlayersSetUp();
    /**
     * Cannot be overridden
     */
    void playersSetUp();
    List<Player> createPlayers();
    void createPlayersHands(List<Player> players);
    void postPlayersSetUp();

    void preGameStart();
    void gameStart();
    void postGameStart();

    /**
     * Cannot be overridden
     */
    void roundCycle();
    boolean isGameActive(Deck deck, List<Player> players);
    void roundStart();
    PlayingField<Card, Player> setUpPlayingField();
    void playTurn(PlayingField<Card, Player> playingField, List<Player> players);
    Player determineRoundWinner(PlayingField<Card, Player> playingField);
    void postRoundWinnerDeterminedActions(Player roundWinner, PlayingField<Card, Player> playingField);
    void drawCards(List<Player> players, Deck deck);
    void roundEnd(PlayingField<Card, Player> playingField, Player roundWinner);

    void preGameEnd();
    void gameEnd();
    List<Player> determineGameWinners(List<Player> players);
    void postGameWinnersDeterminedActions(List<Player> winners);
    void postGameEnd();
}
