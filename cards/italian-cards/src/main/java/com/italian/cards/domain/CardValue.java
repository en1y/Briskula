package com.italian.cards.domain;

import com.template.cards.domain.CardValueInterface;

import java.util.*;


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

    private static Map<CardValue, Integer> cardValueMap = new HashMap<>();

    public static final Comparator<CardValue> cardValueComparator = new Comparator<CardValue>() {
        @Override
        public int compare(CardValue o1, CardValue o2) {
            var isValueEqual = Integer.compare(cardValueMap.get(o1), cardValueMap.get(o2));
            var isNumberEqual = Integer.compare(o1.getNumber(), o2.getNumber());

            if (isValueEqual != 0) {
                return isValueEqual;
            } else {
                return isNumberEqual;
            }
        }
    };

    private final String name;
    private final int number;

    CardValue(String name, int number) {
        this.name = name;
        this.number = number;
    }

    static {
        initializeCardValueMap();
    }

    private static void initializeCardValueMap() {
        for (CardValue cardValue : CardValue.values()) {
            cardValueMap.put(cardValue, cardValue.getNumber());
        }
    }

    public static void setCardValueMap(Map<CardValue, Integer> cardValueMap) {
        Objects.requireNonNull(cardValueMap, "ItalianCard value map cannot be null");

        if (cardValueMap.keySet().containsAll(Arrays.asList(CardValue.values()))) {
            CardValue.cardValueMap = cardValueMap;
        } else {
            throw new IllegalArgumentException("ItalianCard value map should contain all the card values");
        }
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
