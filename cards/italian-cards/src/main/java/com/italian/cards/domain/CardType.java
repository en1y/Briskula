package com.italian.cards.domain;

import com.template.cards.domain.CardTypeInterface;

import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * This enum represents different <b>types</b> of cards.<br>
 * Every card should have a name associated with it.<br>
 * For implementing multiple languages, set the path to the config file with multiple language definitions.<br>
 * The File should be named "CardTypeBundle_{lang}.properties" and placed in the resources directory.<br>
 * Pattern: {CardType.name} = {lang.name}<br>
 * Example: Spades = Spades<br>
 */
public enum CardType implements CardTypeInterface {

    COPPE ("Cups"),
    DENARI ("Coins"),
    SPADE ("Swords"),
    BASTONI ("Clubs");

    private static boolean useResourceBundle = false;
    private static ResourceBundle resourceBundle;

    private final String name;

    CardType(String name) {
        this.name = name;
    }

    /**
     * Use this method if you want to use the resource bundle for card type names.
     * @param locale - with this locale the resource bundle will be loaded
     */
    public static void useResourceBundle(Locale locale) {
        Objects.requireNonNull(locale);
        useResourceBundle = true;

        resourceBundle = ResourceBundle.getBundle("CardTypeBundle", locale);
    }

    /**
     * When the resource bundle is not used, this method returns the name of the card type.
     * When the resource bundle is used, it returns the name from the resource bundle.
     * If the resource bundle does not contain the name, it returns the name of the card type.
     */
    public String getName() {
        if (useResourceBundle && resourceBundle.containsKey(name)) {
            return resourceBundle.getString(name);
        }
        return name;
    }
}
