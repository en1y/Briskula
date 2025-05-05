package domain;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.Ansi.Color;

public enum EventType {
    PLAY (Color.GREEN),
    INFO (Color.CYAN),
    INPUT (Color.GREEN),
    ERROR (Color.RED),
    WARNING (Color.YELLOW);

    public static final int PREFIX_LEN = 10;

    private final Color color;
    private final String prefix;

    EventType(Color color) {
        this.color = color;
        this.prefix = Ansi.ansi().fg(color).a(
                    String.format("[%s]%s",
                            name(),
                            " ".repeat(PREFIX_LEN - 2 - name().length())))
                .reset().toString();
    }

    public Color getColor() {
        return color;
    }

    public String getPrefix() {
        return prefix;
    }
}
