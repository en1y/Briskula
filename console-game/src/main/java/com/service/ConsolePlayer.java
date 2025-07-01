package com.service;

import com.domain.EventType;
import com.domain.inputer.Inputer;
import com.domain.printer.Printer;
import com.italian.cards.ItalianCard;
import com.template.cards.domain.BriskulaPlayer;

import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class ConsolePlayer extends BriskulaPlayer {
    private static ResourceBundle resourceBundle;

    private final Inputer in;
    private final Printer out;

    public ConsolePlayer(int id, String name, Inputer in, Printer out) {
        super(id, name);
        this.in = in;
        this.out = out;
    }

    public static void setLocale(Locale locale) {
        Objects.requireNonNull(locale);

        resourceBundle = ResourceBundle.getBundle("ConsolePlayerBundle", locale);
    }

    public ItalianCard getPlayableCard() {
        out.println(getHand().toString(), EventType.INFO, true);
        while (true){
            out.print(resourceBundle.getString("player.hand.prompt"), EventType.INPUT);
            var input = in.readLine();
            try {
                var card = getHand().getCards().get(Character.getNumericValue(input.charAt(0))-1);
                out.println(String.format(resourceBundle.getString("player.card.selected"), card.toString()), EventType.PLAY);
                return card;
            } catch (Exception ignored) {
                out.println(resourceBundle.getString("player.card.invalid"), EventType.ERROR);
            }
        }
    }

}
