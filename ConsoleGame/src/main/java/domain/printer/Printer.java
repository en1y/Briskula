package domain.printer;

import java.io.OutputStream;
import java.io.PrintStream;

public class Printer {

    private PrintStream out;

    public Printer(OutputStream out) {
        this.out = new PrintStream(out, true);
    }

    public void print(String text) {
        out.print(text);
    }

}
