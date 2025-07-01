package com.template.cards.service;

import base.game.domain.PlayingField;
import com.italian.cards.ItalianCard;
import com.italian.cards.service.ItalianCardFactory;
import com.template.cards.domain.BriskulaCardValue;
import com.template.cards.domain.BriskulaPlayer;
import com.template.cards.domain.Deck;
import base.game.model.AbstractGame;

import java.util.*;

public class BriskulaGame<Player extends BriskulaPlayer> extends AbstractGame<ItalianCard, Player, Deck> {

    private ItalianCard gameTrumpCard;

    public BriskulaGame(int numberOfPlayers, int cardsInHandNum) {
        super(numberOfPlayers, ItalianCardFactory.ITALIAN_DECK_CARD_NUMBER, cardsInHandNum);
    }

    @Override
    public void preGameCreateCheck(int numberOfPlayers, int cardsNum) {
        if (numberOfPlayers < 2 || numberOfPlayers > 4) {
            throw new IllegalArgumentException("Number of players must be between 2 and 4.");
        }
    }

    @Override
    public Deck createDeck(int cardsNum) {
        var deck = new Deck(cardsNum);
        gameTrumpCard = deck.getCards().getLast();
        return deck;
    }

    @Override
    public List<ItalianCard> removeNotNeededCards(Deck deck) {
        var cardsToRemoveNum = deck.getSize() % getNumberOfPlayers();
        return deck.drawXCards(cardsToRemoveNum);
    }

    @Override
//    Method to be overridden to create players
    public List<Player> createPlayers() {
        throw new UnsupportedOperationException("Creating players is not implemented yet. " +
                "To implement it override the Game.createPlayers() method");
    }

    @Override
    public void createPlayersHands(List<Player> players) {
        for (Player player : players) {
            player.getHandFromDeck(getDeck(), getCardsInHandNum());
        }
    }

    @Override
    public boolean isGameActive(Deck deck, List<Player> players) {
        return players.stream().anyMatch(player -> !player.getHand().isEmpty());
    }

    @Override
    public void playTurn(PlayingField<ItalianCard, Player> playingField, List<Player> players) {
        for (var player : players) {
            var card = playerChooseCard(player, playingField);
            playingField.play(card, player);
        }
    }

    public ItalianCard playerChooseCard(Player player, PlayingField<ItalianCard, Player> playingField) {
        var cards = player.getHand().getCards();
        var randomIndex = new Random().nextInt(cards.size());
        return cards.get(randomIndex);
    }

    @Override
    public Player determineRoundWinner(PlayingField<ItalianCard, Player> playingField) {
        var roundTrumpCard = playingField.getPlayedCards().getFirst();
        var strongestCard = playingField.getPlayedCards().getFirst();

        for (var card : playingField.getPlayedCards()) {
            strongestCard = compareCards(roundTrumpCard, strongestCard, card);
        }

        return playingField.getPlayers().get(playingField.getPlayedCards().indexOf(strongestCard));
    }

    private ItalianCard compareCards(ItalianCard roundTrumpCard, ItalianCard card1, ItalianCard card2) {
        var briskulaCardValue = new BriskulaCardValue();

        var isCard1Trump = card1.getType().equals(getGameTrumpCard().getType());
        var isCard2Trump = card2.getType().equals(getGameTrumpCard().getType());


        if (isCard1Trump && !isCard2Trump) {
            return card1;
        } else if (!isCard1Trump && isCard2Trump) {
            return card2;
        } else {
            var card1Value = briskulaCardValue.getValue(card1);
            var card2Value = briskulaCardValue.getValue(card2);
            var card1Number = card1.getValue().getNumber();
            var card2Number = card2.getValue().getNumber();

            if (isCard1Trump && isCard2Trump) {
                if (card1Value == card2Value) return card1Number > card2Number ? card1 : card2;
                else return card1Value > card2Value ? card1 : card2;
            }

            var isCard1TrumpInRound = card1.getType().equals(roundTrumpCard.getType());
            var isCard2TrumpInRound = card2.getType().equals(roundTrumpCard.getType());

            if (isCard1TrumpInRound && !isCard2TrumpInRound) {
                return card1;
            } else if (!isCard1TrumpInRound && isCard2TrumpInRound) {
                return card2;
            } else {
                if (card1Value == card2Value) {
                    return card1Number > card2Number ? card1 : card2;
                } else {
                    return card1Value > card2Value ? card1 : card2;
                }
            }
        }
    }

    @Override
    public void postRoundWinnerDeterminedActions(Player roundWinner, PlayingField<ItalianCard, Player> playingField) {

        var cardValue = new BriskulaCardValue();
        var pointsSum = 0;

        for (var card : playingField.getPlayedCards()) {
            pointsSum += cardValue.getValue(card);
        }

        Collections.rotate(getPlayers(), getPlayers().indexOf(roundWinner)*-1);
        if (getNumberOfPlayers() == 4) {
            getPlayers().get(2).addPoints(pointsSum); // the third player always wins aside the roundWinner, hence they are in a team
        }
        roundWinner.addPoints(pointsSum);
    }

    @Override
    public void drawCards(List<Player> players, Deck deck) {
        if (deck.isEmpty()) return;
        for (var player : players) {
            player.getHand().addCard(
                    deck.drawCard()
            );
        }
    }

    @Override
    public List<Player> determineGameWinners(List<Player> players) {

        if (getNumberOfPlayers() == 4) {
            var res = new ArrayList<Player>();

            var firstPlayer = players.getFirst();
            var lastPlayer = players.getLast();

            if (firstPlayer.getPoints() == lastPlayer.getPoints()) {
                return players;
            }

            var winner = firstPlayer.getPoints() > players.getLast().getPoints() ? firstPlayer : players.getLast();
            if (winner.equals(firstPlayer)) {
                res.add(firstPlayer);
                res.add(players.get(2));
            }
            else if (winner.equals(players.getLast())) {
                res.add(firstPlayer);
                res.add(players.get(2));
            }
            return res;
        }

        return List.of(players.stream().max(Comparator.comparingInt(Player::getPoints))
                .orElseThrow(() -> new IllegalStateException("No winning player found.")));

    }

    public ItalianCard getGameTrumpCard() {
        return gameTrumpCard;
    }
}
