import com.italian.cards.ItalianCard;
import com.service.ConsoleGame;

import java.util.Locale;

public class App {
    public static void main(String[] args) {
        System.out.print("Welcome to Briskula! Select your language(en/hr/ua/ru): ");
        var scanner = new java.util.Scanner(System.in);
        var selectedLanguage = scanner.nextLine();
        Locale locale;

        if (selectedLanguage.isBlank()) {
            System.out.println("No language selected, defaulting to English.");
            locale = (Locale.ENGLISH);
        } else if ("hr".startsWith(selectedLanguage.toLowerCase())) {
            System.out.println("Language set to Croatian.");
            locale = (Locale.forLanguageTag("hr-HR"));
        } else if ("en".startsWith(selectedLanguage.toLowerCase())) {
            System.out.println("Language set to English.");
            locale = (Locale.ENGLISH);
        } else if ("ua".startsWith(selectedLanguage.toLowerCase())) {
            System.out.println("Language set to Ukrainian.");
            locale = (Locale.forLanguageTag("uk-UA"));
        } else if ("ru".startsWith(selectedLanguage.toLowerCase())) {
            System.out.println("Language set to Russian.");
            System.out.println("Мда, русский язык не поддерживается, но все равно его используем.");
            locale = (Locale.forLanguageTag("ru-RU"));
        }
        else {
            System.out.println("Unsupported language, defaulting to English.");
            locale = (Locale.ENGLISH);
        }

        var game = new ConsoleGame(4, System.out, System.in, locale);
        game.start();
    }
}
