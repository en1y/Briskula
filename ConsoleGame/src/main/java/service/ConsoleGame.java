package service;

import java.io.OutputStream;

public class ConsoleGame extends Game {

    public ConsoleGame(int numberOfPlayers, int cardsNum, OutputStream output) {
        super(numberOfPlayers, cardsNum);
    }
}
