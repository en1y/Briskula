import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * This enum should be overridden by your code with added card values (e.g., ACE, TWO, THREE, FOUR, FIVE, etc.).<br>
 * It represents different <b>values</b> of cards.<br>
 * Values are automatically sorted in ascending order. (So you should put them from the weakest to the strongest.)<br>
 * Every card should have a value associated with it.<br>
 * For implementing multiple languages, set the path to the config file with multiple language definitions.<br>
 * The File should be named "CardValueBundle{lang}.properties" and placed in the resources directory.<br>
 * Pattern: {CardValue.name} = {lang.name}<br>
 * Example: Two = Two<br>
 */
public enum CardValue {

    ; // Add your card values here, e.g., ACE("Ace", 1), TWO("Two", 2), THREE("Three", 3), etc.

    private static boolean useResourceBundle = false;
    private static ResourceBundle resourceBundle;

    private final String name;
    private final int value;

    CardValue(String name, int value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Use this method if you want to use the resource bundle for card value names and values.
     * @param locale - with this locale the resource bundle will be loaded
     */
    public static void useResourceBundle(Locale locale) {
        Objects.requireNonNull(locale);
        useResourceBundle = true;

        resourceBundle = ResourceBundle.getBundle("CardValueBundle", locale);
    }

    /**
     * When the resource bundle is not used, this method returns the name of the card value.
     * When the resource bundle is used, it returns the name from the resource bundle.
     * If the resource bundle does not contain the name, it returns the name of the card value.
     */
    public String getName() {
        if (useResourceBundle && resourceBundle.containsKey(name)) {
            return resourceBundle.getString(name);
        }
        return name;
    }

    public int getValue() {
        return value;
    }
}
