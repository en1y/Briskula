package service.model;

import com.AbstractCard;
import domain.Deck;
import domain.PlayingField;
import service.model.interfaces.GameInterface;

import java.util.ArrayList;
import java.util.List;

// You should implement the Locale for the cards you are using.
public abstract class AbstractGame
        <Card extends AbstractCard, Player extends AbstractPlayer<Card>>
        implements GameInterface<Card, Player> {

    private Deck<Card> deck;
    private List<Player> players;
    private final int numberOfPlayers;
    private final int cardsNum;

    public AbstractGame(int numberOfPlayers, int cardsNum) {
        this.numberOfPlayers = numberOfPlayers;
        this.cardsNum = cardsNum;
        players = new ArrayList<>();
    }

    @Override
    public final void start() {
        deckSetUp();
        playersSetUp();
        gameStart();
        gameCycle();
        gameEnd();
    }


    @Override
    public final void deckSetUp() {
        preDeckSetUp();
        deck = createDeck(cardsNum);
        removeNotNeededCards(deck);
        postDeckSetUp();
    }

    @Override public void preDeckSetUp() {}
    @Override public void removeNotNeededCards(Deck<Card> deck) {}
    @Override public void postDeckSetUp() {}

    @Override
    public Deck<Card> createDeck(int cardsNum) {
        throw new UnsupportedOperationException("""
                Creating the deck is not implemented yet.
                To implement it override the Game.createDeck() method""");
    }

    @Override
    public final void playersSetUp() {
        prePlayersSetUp();
        players = createPlayers();
        createPlayersHands(players);
        postPlayersSetUp();
    }

    @Override public void prePlayersSetUp() {}
    @Override public void postPlayersSetUp() {}

    @Override
    public List<Player> createPlayers() {
        throw new UnsupportedOperationException("""
                Creating the players is not implemented yet.
                To implement it override the Game.createPlayers() method""");
    }

    @Override
    public void createPlayersHands(List<Player> players) {
        throw new UnsupportedOperationException("""
                Creating players hands is not implemented yet.
                To implement it override the Game.createPlayersHands() method""");
    }

    @Override
    public final void gameStart() {
        preGameStart();
        postGameStart();
    }

    @Override
    public final void gameCycle() {
        while (isGameActive(deck, players)) {
            gameCycleStart();
            var playingField = setUpPlayingField();
            playTurn(playingField, players);
            var winner = determineRoundWinner(playingField);
            postRoundWinnerDeterminedActions(winner);
            drawCards(players, deck);
            gameCycleEnd();
        }
    }

    @Override public void gameCycleStart() {}
    @Override public Player determineRoundWinner(PlayingField<Card, Player> playingField) {return null;}
    @Override public void postRoundWinnerDeterminedActions(Player winner) {}
    @Override public void drawCards(List<Player> players, Deck<Card> deck) {}
    @Override public void gameCycleEnd() {}

    @Override
    public boolean isGameActive(Deck<Card> deck, List<Player> players) {
        throw new UnsupportedOperationException("""
                Is game active check is not implemented yet.
                To implement it override the Game.isGameActive() method""");
    }

    @Override
    public PlayingField<Card, Player> setUpPlayingField() {
        throw new UnsupportedOperationException("""
                Setting up playing field is not implemented yet.
                To implement it override the Game.setUpPlayingField() method""");
    }

    @Override
    public void playTurn(PlayingField<Card, Player> playingField, List<Player> players) {
        throw new UnsupportedOperationException("""
                Playing a turn is not implemented yet.
                To implement it override the Game.playTurn() method""");
    }

    @Override
    public final void gameEnd(){
        preGameEnd();
        var winner = determineGameWinner();
        postGameWinnerDeterminedActions(winner);
        postGameEnd();
    }

    @Override public void preGameEnd() {}
    @Override public Player determineGameWinner() {return null;}
    @Override public void postGameWinnerDeterminedActions(Player winner) {}
    @Override public void postGameEnd() {}

    public Deck<Card> getDeck() {
        return deck;
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }
}
