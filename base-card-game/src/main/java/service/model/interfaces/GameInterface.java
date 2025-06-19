package service.model.interfaces;

import com.AbstractCard;
import domain.Deck;
import domain.PlayingField;
import service.model.AbstractPlayer;

import java.util.List;

public interface GameInterface <Card extends AbstractCard, Player extends AbstractPlayer<Card>> {

    /**
     * The main method that starts the game <br>
     * Cannot be overridden<br>
     */
    void start();

    void preDeckSetUp();
    /**
     * Cannot be overridden
     */
    void deckSetUp();
    Deck<Card> createDeck(int cardsNum);
    void removeNotNeededCards(Deck<Card> deck);
    void postDeckSetUp();

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
    void gameCycle();
    boolean isGameActive(Deck<Card> deck, List<Player> players);
    void gameCycleStart();
    PlayingField<Card, Player> setUpPlayingField();
    void playTurn(PlayingField<Card, Player> playingField, List<Player> players);
    Player determineRoundWinner(PlayingField<Card, Player> playingField);
    void postRoundWinnerDeterminedActions(Player winner);
    void drawCards(List<Player> players, Deck<Card> deck);
    void gameCycleEnd();

    void preGameEnd();
    void gameEnd();
    Player determineGameWinner();
    void postGameWinnerDeterminedActions(Player winner);
    void postGameEnd();
}
