package service;

import domain.CardsInPlay;
import domain.EventType;
import domain.Hand;
import domain.inputer.Inputer;
import domain.printer.Printer;

public class ConsolePlayer extends Player {
    private final Inputer in;
    private final Printer out;

    public ConsolePlayer(Hand hand, int id, Inputer in, Printer out) {
        super(hand, id);
        this.in = in;
        this.out = out;
    }

    @Override
    public void playCard(CardsInPlay cip) {
        out.println(getHand().toCroatianString(), EventType.INFO, true);
        while (true){
            out.print("Unesi id karte koju želiš odigrati: ", EventType.INPUT);
            var input = in.readLine();
            try {
                var card = getHand().getCard(Character.getNumericValue(input.charAt(0))-1);
                out.println(String.format("Odabrana karta: %s", card.toCroatianString()), EventType.PLAY);
                cip.play(card, this);
                return;
            } catch (Exception ignored) {
                out.println("Nevalidan unos pička ti materina", EventType.ERROR);
            }
        }
    }
}
