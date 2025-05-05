package service;

import domain.Card;
import domain.CardsInPlay;
import domain.Deck;
import domain.EventType;
import domain.inputer.Inputer;
import domain.printer.Printer;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayDeque;
import java.util.Queue;

public class ConsoleGame extends Game {
    private final Inputer in;
    private final Printer out;
    private final Queue<ConsolePlayer> players;
    private Deck deck;
    private Card trumpCard;

    public ConsoleGame(int numberOfPlayers, int cardsNum, OutputStream output, InputStream input) {
        super(numberOfPlayers, cardsNum);
        in = new Inputer(input);
        out = new Printer(output);
        players = new ArrayDeque<>();
    }

    @Override
    public void start() {
        var english = "english";
        var croatian = "hrvatski";
        var lang = chooseFromOptions("Choose language (en/hr): ", english, croatian);
        if (lang.equals(croatian)) {
            startCroatian();
        } else if (lang.equals(english)) {

        } else {
            out.print("Invalid option", EventType.ERROR);
        }
    }

    private String chooseFromOptions(String message, String... options) {
        while (true) {
            out.print(message, EventType.INPUT);
            var input = in.readLine();
            String res = null;

            for (var option : options) {
                if (option.toLowerCase().startsWith(input.toLowerCase())) {
                    res = option;
                    break;
                }
            }

            if (res != null) {
                return res;
            }
            out.println("Invalid option", EventType.WARNING);
        }
    }

    public void startCroatian() {
        deck = new Deck();

        for (int i = 0; i < getNumberOfPlayers(); i++) {

            var player = new ConsolePlayer(
                    deck.createHand(getCardsNum()),
                    in, out
            );
            players.add(player);

            out.print(String.format("Igrač %d, unesi svoje ime: ", i + 1), EventType.INPUT);
            player.setName(in.readLine());
            out.println(String.format("Dobro došli, %s!", player.getName()), EventType.INFO);
            out.println();
        }

        trumpCard = deck.getTrumpCard();

        out.println(String.format("Adut je: %s", trumpCard.toCroatianString()), EventType.INFO);
        out.println();

        while (!deck.isEmpty()) {

            var cip = new CardsInPlay(deck, getNumberOfPlayers());

            for (var player: players) {
                out.println(String.format("Sad igra %s.", player.getName()), EventType.INFO);
                cip.play(player.playCardCroatian(cip), player);
                out.println();
            }
            var winner = (cip.determineWinner(trumpCard.type()));
            winner.addPoints(cip.getNumberOfPoints());

            out.println(String.format("Pobjedio je %s sa %d pointa!", winner.getName(), cip.getNumberOfPoints()), EventType.INFO);

            while (!players.peek().equals(winner)) {
                players.add(players.poll());
            }

            for (var player: players) {
                player.getHand().addCard(
                        deck.drawCard()
                );
            }
        }

        var winner = players.peek();

        for (var player : players) {
            winner = winner.getPoints() > player.getPoints() ? winner : player;
        }

        out.println(String.format("Pobjedio je %s sa %d pointa!", winner.getName(), winner.getPoints()), EventType.INFO);

    }

}
