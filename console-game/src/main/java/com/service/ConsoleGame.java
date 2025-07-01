package com.service;

import base.game.domain.PlayingField;
import com.domain.EventType;
import com.domain.inputer.Inputer;
import com.domain.printer.Printer;
import com.italian.cards.ItalianCard;
import com.template.cards.service.BriskulaGame;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class ConsoleGame extends BriskulaGame<ConsolePlayer> {

    private final Inputer in;
    private final Printer out;

    public ConsoleGame(int numberOfPlayers, int cardsInHandNum, OutputStream output, InputStream input) {
        super(numberOfPlayers, cardsInHandNum);
        in = new Inputer(input);
        out = new Printer(output);
        playersSetUp();
    }

    public ConsoleGame(int numberOfPlayers, OutputStream output, InputStream input) {
        super(numberOfPlayers, 3);
        in = new Inputer(input);
        out = new Printer(output);
        playersSetUp();
    }

    @Override
    public List<ConsolePlayer> createPlayers() {
        var players = new ArrayList<ConsolePlayer>(getNumberOfPlayers());

        for (int i = 0; i < getNumberOfPlayers(); i++) {
            out.print(String.format("Player %d, write your name: ", i+1), EventType.INPUT);
            var input = in.readLine().strip();
            if (input.isEmpty()) {
                out.println("You must enter a username!", EventType.ERROR);
                i--;
                continue;
            }
            var player = new ConsolePlayer(
                    i, input, in, out
            );
            players.add(player);
            out.println(String.format("Welcum, %s!", player.getName()), EventType.INFO);
            out.println();
        }

        return players;
    }

    @Override
    public void roundStart() {
        if (!getDeck().isEmpty())
            out.println(
                    String.format("Card %s is the trump card.", getGameTrumpCard().toString()), EventType.RESULT);
    }

    @Override
    public ItalianCard playerChooseCard(ConsolePlayer player, PlayingField<ItalianCard, ConsolePlayer> playingField) {
        var playingFieldString = playingField.toString();
        var printField = playingFieldString.isEmpty() ? "No cards have been played yet." : "Played cards:\n" + playingFieldString + "\n";
        out.println();
        out.println(printField, EventType.INFO, true);
        out.println(String.format("Now is %s turn: ", player.getName()), EventType.INFO);
        out.println();
        return player.getPlayableCard();
    }

    @Override
    public void roundEnd(PlayingField<ItalianCard, ConsolePlayer> playingField, ConsolePlayer roundWinner) {
        out.println("Final played cards:", EventType.RESULT);
        out.println(playingField.toString(), EventType.RESULT, true);
        out.println(String.format("%s won this round with %s.", roundWinner.getName(), playingField.getCardPlayedBy(roundWinner)), EventType.RESULT);
        out.println();
    }

    @Override
    public void postGameWinnersDeterminedActions(List<ConsolePlayer> winners) {
        if (winners.isEmpty()) {
            out.println("No winners this time.", EventType.RESULT);
        } else {
            var winnersStr = new StringJoiner(" and ");
            for (var winner : winners) {
                winnersStr.add(winner.getName());
            }
            out.println(String.format("%s won with %d points!", winnersStr, winners.getFirst().getPoints()), EventType.RESULT);
        }

        while (true) {
            out.print("Do you want to play one more game? (y/N): ", EventType.INPUT);
            var input = in.readLine();
            if (input.isEmpty()) {
                continue;
            }
            if ("yes".startsWith(input.toLowerCase())) {
                System.gc();
                restart();
            } else if ("no".startsWith(input.toLowerCase())) {
                out.println("May the 4th be with you!", EventType.INFO);
                System.exit(0);
            }
        }
    }
}
