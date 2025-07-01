package com.service;

import base.game.domain.PlayingField;
import com.domain.EventType;
import com.domain.inputer.Inputer;
import com.domain.printer.Printer;
import com.italian.cards.ItalianCard;
import com.template.cards.service.BriskulaGame;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

public class ConsoleGame extends BriskulaGame<ConsolePlayer> {

    private static ResourceBundle resourceBundle;

    private final Inputer in;
    private final Printer out;

    public ConsoleGame(int numberOfPlayers, int cardsInHandNum, OutputStream output, InputStream input, Locale locale) {
        super(numberOfPlayers, cardsInHandNum);
        in = new Inputer(input);
        out = new Printer(output);
        setLocale(locale);
        playersSetUp();
    }

    public ConsoleGame(int numberOfPlayers, OutputStream output, InputStream input) {
        this(numberOfPlayers, 3, output, input, Locale.ENGLISH);
    }

    public ConsoleGame(int numberOfPlayers, OutputStream output, InputStream input, Locale locale) {
        this(numberOfPlayers, 3, output, input, locale);
    }

    public void setLocale(Locale locale) {
        Objects.requireNonNull(locale);

        resourceBundle = ResourceBundle.getBundle("ConsoleGameBundle", locale);

        EventType.setLocale(locale);
        ItalianCard.setLocale(locale);
        ConsolePlayer.setLocale(locale);
    }

    @Override
    public List<ConsolePlayer> createPlayers() {
        var players = new ArrayList<ConsolePlayer>(getNumberOfPlayers());

        for (int i = 0; i < getNumberOfPlayers(); i++) {
            out.print(String.format(resourceBundle.getString("player.name.prompt"), i+1), EventType.INPUT);
            var input = in.readLine().strip();
            if (input.isEmpty()) {
                out.println(resourceBundle.getString("player.name.empty"), EventType.ERROR);
                i--;
                continue;
            }
            var player = new ConsolePlayer(
                    i, input, in, out
            );
            players.add(player);
            out.println(String.format(resourceBundle.getString("player.welcome"), player.getName()), EventType.INFO);
            out.println();
        }
        return players;
    }

    @Override
    public void roundStart() {
        if (!getDeck().isEmpty())
            out.println(
                    String.format(resourceBundle.getString("game.trump.card"), getGameTrumpCard().toString()), EventType.RESULT);
    }

    @Override
    public ItalianCard playerChooseCard(ConsolePlayer player, PlayingField<ItalianCard, ConsolePlayer> playingField) {
        var playingFieldString = playingField.toString();
        var printField = playingFieldString.isEmpty() ? resourceBundle.getString("field.empty") : resourceBundle.getString("field.nonempty") + playingFieldString + "\n";
        out.println();
        out.println(printField, EventType.INFO, true);
        out.println(String.format(resourceBundle.getString("turn.prompt"), player.getName()), EventType.INFO);
        out.println();
        return player.getPlayableCard();
    }

    @Override
    public void roundEnd(PlayingField<ItalianCard, ConsolePlayer> playingField, ConsolePlayer roundWinner) {
        out.println(resourceBundle.getString("round.cards.final"), EventType.RESULT);
        out.println(playingField.toString(), EventType.RESULT, true);
        out.println(String.format(resourceBundle.getString("round.winner"), roundWinner.getName(), playingField.getCardPlayedBy(roundWinner)), EventType.RESULT);
        out.println();
    }

    @Override
    public void postGameWinnersDeterminedActions(List<ConsolePlayer> winners) {
        if (winners.isEmpty()) {
            out.println(resourceBundle.getString("game.no.winner"), EventType.RESULT);
        } else {
            var winnersStr = new StringJoiner(" and ");
            for (var winner : winners) {
                winnersStr.add(winner.getName());
            }
            out.println(String.format(resourceBundle.getString("game.winner"), winnersStr, winners.getFirst().getPoints()), EventType.RESULT);
        }

        while (true) {
            out.print(resourceBundle.getString("replay.prompt"), EventType.INPUT);
            var input = in.readLine();
            if (input.isEmpty()) {
                continue;
            }
            if ("yes".startsWith(input.toLowerCase())) {
                System.gc();
                restart();
            } else if ("no".startsWith(input.toLowerCase())) {
                out.println(resourceBundle.getString("replay.exit"), EventType.INFO);
                System.exit(0);
            }
        }
    }
}
