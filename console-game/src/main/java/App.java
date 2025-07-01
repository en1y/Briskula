import com.italian.cards.ItalianCard;
import com.service.ConsoleGame;

import java.util.Locale;

public class App {
    public static void main(String[] args) {
        ItalianCard.setLocale(Locale.forLanguageTag("hr-HR"));
        new ConsoleGame(4, System.out, System.in).start();
    }
}
