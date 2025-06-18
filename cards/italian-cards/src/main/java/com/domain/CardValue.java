package com.domain;

import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;


/**
 * This enum represents different <b>values</b> of cards.<br>
 * Values are automatically sorted in ascending order.
 * For implementing multiple languages, set the path to the config file with multiple language definitions.<br>
 * The File should be named "CardValueBundle_{lang}.properties" and placed in the resources directory.<br>
 * Pattern: {CardValue.name}={lang.name}<br>
 * Example: Two = Two<br>
 */
public enum CardValue implements CardValueInterface {
    ACE ("Ace", 1),
    TWO ("Two", 2),
    THREE ("Three", 3),
    FOUR ("Four", 4),
    FIVE ("Five", 5),
    SIX ("Six", 6),
    SEVEN ("Seven", 7),
    JACK ("Jack", 11),
    KNIGHT ("Knight", 12),
    KING ("King", 13);

    private static boolean useResourceBundle = false;
    private static ResourceBundle resourceBundle;

    private final String name;
    private final int number;

    CardValue(String name, int number) {
        this.name = name;
        this.number = number;
    }

    /**
     * Use this method if you want to use the resource bundle for card number names and values.
     * @param locale - with this locale the resource bundle will be loaded
     */
    public static void useResourceBundle(Locale locale) {
        Objects.requireNonNull(locale);
        useResourceBundle = true;

        resourceBundle = ResourceBundle.getBundle("CardValueBundle", locale);
    }

    /**
     * When the resource bundle is not used, this method returns the name of the card number.
     * When the resource bundle is used, it returns the name from the resource bundle.
     * If the resource bundle does not contain the name, it returns the name of the card number.
     */
    public String getName() {
        if (useResourceBundle && resourceBundle.containsKey(name)) {
            return resourceBundle.getString(name);
        }
        return name;
    }

    public int getNumber() {
        return number;
    }
}
