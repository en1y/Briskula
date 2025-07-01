package base.game.model;

import base.game.domain.AbstractDeck;
import com.template.cards.AbstractCard;
import base.game.domain.PlayingField;
import base.game.model.interfaces.GameInterface;

import java.util.ArrayList;
import java.util.List;

// You should implement the Locale for the cards you are using.
public abstract class AbstractGame
        <Card extends AbstractCard, Player extends AbstractPlayer<Card>, Deck extends AbstractDeck<Card>>
        implements GameInterface<Card, Player, Deck> {

    private Deck deck;
    private List<Player> players;
    private final int numberOfPlayers;
    private final int cardsNum;
    private final int cardsInHandNum;

    public AbstractGame(int numberOfPlayers, int cardsNum, int cardsInHandNum) {
        this.numberOfPlayers = numberOfPlayers;
        this.cardsNum = cardsNum;
        this.cardsInHandNum = cardsInHandNum;
        players = new ArrayList<>();
        preGameCreateCheck(numberOfPlayers, cardsNum);
        deckSetUp();
        players = new ArrayList<>();
    }

    @Override
    public void preGameCreateCheck(int numberOfPlayers, int cardsNum) {}

    @Override
    public final void start() {
        if (players.isEmpty()) {
            playersSetUp();
        }
        gameStart();
        roundCycle();
        gameEnd();
    }

    public final void restart() {
        deckSetUp();
        if (players.isEmpty()) {
            playersSetUp();
        }
        gameStart();
        roundCycle();
        gameEnd();
    }


    @Override
    public final void deckSetUp() {
        preDeckSetUp();
        deck = createDeck(cardsNum);
        var removedCards = removeNotNeededCards(deck);
        postDeckSetUp(removedCards);
    }

    @Override public void preDeckSetUp() {}
    @Override public List<Card> removeNotNeededCards(Deck deck) { return new ArrayList<>(); }
    @Override public void postDeckSetUp(List<Card> removedCards) {}

    @Override
    public Deck createDeck(int cardsNum) {
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
    public void preGameStart(){}
    @Override
    public void postGameStart(){}

    @Override
    public final void roundCycle() {
        while (isGameActive(deck, players)) {
            roundStart();
            var playingField = setUpPlayingField();
            playTurn(playingField, players);
            var roundWinner = determineRoundWinner(playingField);
            postRoundWinnerDeterminedActions(roundWinner, playingField);
            drawCards(players, deck);
            roundEnd(playingField, roundWinner);
        }
    }

    @Override public void roundStart() {}
    @Override public Player determineRoundWinner(PlayingField<Card, Player> playingField) {return null;}
    @Override public void postRoundWinnerDeterminedActions(Player roundWinner, PlayingField<Card, Player> playingField) {}
    @Override public void drawCards(List<Player> players, Deck deck) {}
    @Override public void roundEnd(PlayingField<Card, Player> playingField, Player roundWinner) {}

    @Override
    public boolean isGameActive(Deck deck, List<Player> players) {
        throw new UnsupportedOperationException("""
                Is game active check is not implemented yet.
                To implement it override the Game.isGameActive() method""");
    }

    @Override
    public PlayingField<Card, Player> setUpPlayingField() {
        return new PlayingField<>();
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
        var winners = determineGameWinners(players);
        postGameWinnersDeterminedActions(winners);
        postGameEnd();
    }

    @Override public void preGameEnd() {}
    @Override public List<Player> determineGameWinners(List<Player> players) {return null;}
    @Override public void postGameWinnersDeterminedActions(List<Player> winners) {}
    @Override public void postGameEnd() {}

    public Deck getDeck() {
        return deck;
    }

    public int getCardsInHandNum() {
        return cardsInHandNum;
    }

    public int getCardsNum() {
        return cardsNum;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }
}
