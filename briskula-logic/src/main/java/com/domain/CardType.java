package com.domain;

public enum CardType {
    SPADE (
            "spade",
            "špada"
    ),
    COPPE (
            "coppe",
            "kupa"
    ),
    DANARI (
            "danari",
            "dinara"
    ),
    BASTONI (
            "bastoni",
            "baštona"
    );

    private final String name;
    private final String croatianName;

    CardType(String name, String croatianName) {
        this.name = name;
        this.croatianName = croatianName;
    }

    public String getName() {
        return name;
    }

    public String getCroatianName() {
        return croatianName;
    }
}
