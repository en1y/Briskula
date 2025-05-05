package cards;

public enum CardValue {
    ACE(
            11,
            "ace",
            "as"
    ),
    TWO(
            0,
            "two",
            "dva"
    ),
    THREE(
            10,
            "three",
            "tri"
    ),
    FOUR(
            0,
            "four",
            "četiri"
    ),
    FIVE(
            0,
            "five",
            "pet"
    ),
    SIX(
            0,
            "six",
            "sest"
    ),
    SEVEN(
            0,
            "seven",
            "sedam"
    ),
    ELEVEN(
            2,
            "eleven",
            "jedanaest"
    ),
    TWELVE(
            3,
            "twelve",
            "dvanaest"
    ),
    THIRTEEN(
            4,
            "thirteen",
            "trinaest"
    );

    private byte value;
    private String name;
    private String croatianName;

    CardValue(int value, String name, String croatianName) {
        this.value = (byte) value;
        this.name = name;
        this.croatianName = croatianName;
    }

    public byte getValue() {
        return value;
    }

    public void setValue(byte value) {
        this.value = value;
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
