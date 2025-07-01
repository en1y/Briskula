package com.domain;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.Ansi.Color;

import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public enum EventType {
    PLAY (Color.GREEN),
    INFO (Color.CYAN),
    INPUT (Color.GREEN),
    RESULT (Color.RED),
    ERROR (Color.RED),
    WARNING (Color.YELLOW);

    public static final int PREFIX_LEN = 16;
    private static ResourceBundle resourceBundle = null;

    public static void setLocale(Locale locale) {
        Objects.requireNonNull(locale);
        resourceBundle = ResourceBundle.getBundle("EventTypeBundle", locale);

        for(var eventType : EventType.values()) {
            eventType.calculatePrefix();
        }
    }

    private final Color color;
    private String prefix;

    EventType(Color color) {
        this.color = color;
        this.prefix = Ansi.ansi().fg(color).a(
                    String.format("[%s]%s",
                            name(),
                            " ".repeat(PREFIX_LEN - 2 - name().length())))
                .reset().toString();
    }

    private void calculatePrefix() {
        var name = resourceBundle.getString(this.name());

        this.prefix = Ansi.ansi().fg(color).a(
                        String.format("[%s]%s",
                                name,
                                " ".repeat(PREFIX_LEN - 2 - name.length())))
                .reset().toString();
    }

    public Color getColor() {
        return color;
    }

    public String getPrefix() {
        return prefix;
    }
}
