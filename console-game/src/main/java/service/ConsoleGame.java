package service;

import domain.CardsInPlay;
import domain.EventType;
import domain.inputer.Inputer;
import domain.printer.Printer;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

public class ConsoleGame extends Game {
    private final Inputer in;
    private final Printer out;

    public ConsoleGame(int numberOfPlayers, int cardsNum, OutputStream output, InputStream input) {
        super(numberOfPlayers, cardsNum);
        in = new Inputer(input);
        out = new Printer(output);
    }

    @Override
    public List<Player> createPlayers() {
        var players = new ArrayList<Player>(getNumberOfPlayers());
        for (int i = 0; i < getNumberOfPlayers(); i++) {
            players.add(new ConsolePlayer(
                    getDeck().createHand(getCardsNum()),
                    i, in, out
            ));
        }
        return players;
    }

    @Override
    public void setPlayerNames() {
        for (var player : getPlayers()) {
            out.print(String.format("Igrač %d, unesi svoje ime: ", player.getId()+1), EventType.INPUT);
            player.setName(
                in.readLine()
            );
            out.println(String.format("Dobro došli, %s!", player.getName()), EventType.INFO);
            out.println();
        }
    }

    @Override
    public void pregameRoutine() {
        out.println(String.format("Karta %s je adut.\n", getTrumpCard().toCroatianString()), EventType.INFO);
    }

    @Override
    public void gameCycle(CardsInPlay cip) {
        for (var player: getPlayers()) {
            out.println(cip.toCroatianString(), EventType.INFO, true);
            out.println();
            out.println(String.format("Sad igra %s.", player.getName()), EventType.INFO);
            player.playCard(cip);
            out.println();
        }
    }

    @Override
    public void endOfRoundRoutine(Player winner, CardsInPlay cip) {
        out.println("Končne odigrane karte: ", EventType.RESULT);
        out.println(cip.toCroatianString(), EventType.RESULT, true);
        out.println(String.format("Dobio je %s sa %d pointa!", winner.getName(), cip.getNumberOfPoints()), EventType.RESULT);
        out.println();

        pregameRoutine();
    }

    @Override
    public void endOfGame(Player winner) {
        out.println(String.format("Pobjedio je %s sa %d pointa!", winner.getName(), winner.getPoints()), EventType.INFO);
        out.print("Želite li igrati još jednu igru? (y/N): ", EventType.INPUT);
        while (true) {
            out.print("Želite li igrati još jednu igru? (y/N): ", EventType.INPUT);
            var input = in.readLine();
            if (input.isEmpty()) {
                continue;
            }
            if ("yes".startsWith(input.toLowerCase())) {
                System.gc();
                start();
            } else if ("no".startsWith(input.toLowerCase())) {
                System.exit(0);
            }
        }
    }
}
