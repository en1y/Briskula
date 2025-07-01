package com.service;

import com.domain.EventType;
import com.domain.inputer.Inputer;
import com.domain.printer.Printer;
import com.italian.cards.ItalianCard;
import com.template.cards.domain.BriskulaPlayer;

public class ConsolePlayer extends BriskulaPlayer {
    private final Inputer in;
    private final Printer out;

    public ConsolePlayer(int id, String name, Inputer in, Printer out) {
        super(id, name);
        this.in = in;
        this.out = out;
    }

    public ItalianCard getPlayableCard() {
        out.println(getHand().toString(), EventType.INFO, true);
        while (true){
            out.print("Input the id of the card you are willing to play: ", EventType.INPUT);
            var input = in.readLine();
            try {
                var card = getHand().getCards().get(Character.getNumericValue(input.charAt(0))-1);
                out.println(String.format("Chosen card is: %s", card.toString()), EventType.PLAY);
                return card;
            } catch (Exception ignored) {
                out.println("Invalid input brotha.", EventType.ERROR);
            }
        }
    }

}
