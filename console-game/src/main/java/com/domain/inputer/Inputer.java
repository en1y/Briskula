package com.domain.inputer;

import java.io.InputStream;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Inputer {

    public Scanner sc;

    public Inputer(InputStream in) {
        this.sc = new Scanner(in);
    }

    public String readLine() {
        return sc.nextLine();
    }

    public int readInt() {
        return sc.nextInt();
    }

}
