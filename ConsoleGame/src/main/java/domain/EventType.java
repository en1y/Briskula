package domain;

import org.fusesource.jansi.Ansi.Color;

public enum EventType {
    PLAY (Color.GREEN),
    INFO (Color.CYAN),
    INPUT (Color.GREEN),
    ERROR (Color.RED),
    WARNING (Color.YELLOW);

    public static final int PREFIX_LEN = 20;

    private final Color color;
    private final String prefix;

    EventType(Color color) {
        this.color = color;
        this.prefix =
                String.format("[%s]%s",
                        name(),
                        " ".repeat(PREFIX_LEN - 2 - name().length()));
    }

    public Color getColor() {
        return color;
    }

    public String getPrefix() {
        return prefix;
    }
}
