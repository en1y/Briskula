package service;

import domain.Card;
import domain.CardsInPlay;
import domain.EventType;
import domain.Hand;
import domain.inputer.Inputer;
import domain.printer.Printer;

public class ConsolePlayer extends Player {
    private final Inputer in;
    private final Printer out;

    public ConsolePlayer(Hand hand, Inputer in, Printer out) {
        super(hand);
        this.in = in;
        this.out = out;
    }

    public Card playCardCroatian (CardsInPlay cip) {
        out.println(cip.toCroatianString(), EventType.INFO, true);
        out.println(getHand().toCroatianString(), EventType.INFO, true);
        while (true){
            out.print("Unesi id karte koju želiš odigrati: ", EventType.INPUT);
            var input = in.readLine();
            try {
                var card = getHand().getCard(Character.getNumericValue(input.charAt(0))-1);
                out.println(String.format("Odabrana karta: %s", card.toCroatianString()), EventType.PLAY);
                return card;
            } catch (Exception ignored) {
                out.println("Nevalidan unos pička ti materina", EventType.ERROR);
            }
        }
    }
}
