package domain.printer;

import domain.EventType;

import java.io.OutputStream;
import java.io.PrintStream;

public class Printer {

    private PrintStream out;

    public Printer(OutputStream out) {
        this.out = new PrintStream(out, true);
    }

    public void print(String text, EventType type) {
        out.printf("%s%s", type.getPrefix(), text);
    }

    public void println(String text, EventType type, boolean splitOnNewLine) {
        if (!splitOnNewLine) println(text, type);
        else {
            var split = text.split("\n");
            for (var s : split) {
                println(s, type);
            }
        }
    }

    public void println(String text, EventType type) {
        out.printf("%s%s\n", type.getPrefix(), text);
    }

    public void println() {
        out.println();
    }

}
