package domain;

public enum CardType {
    SPADE (
            "spade",
            "špade"
    ),
    COPPE (
            "coppe",
            "kupe"
    ),
    DANARI (
            "danari",
            "dinari"
    ),
    BASTONI (
            "bastoni",
            "baštoni"
    );

    private String name;
    private String croatianName;

    CardType(String name, String croatianName) {
        this.name = name;
        this.croatianName = croatianName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCroatianName() {
        return croatianName;
    }

    public void setCroatianName(String croatianName) {
        this.croatianName = croatianName;
    }
}
