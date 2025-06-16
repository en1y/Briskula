package domain;

public enum CardValue {
    ACE(
            1,
            11
    ),
    TWO(
            2,
            0
    ),
    THREE(
            3,
            10
    ),
    FOUR(
            4,
            0
    ),
    FIVE(
            5,
            0
    ),
    SIX(
            6,
            0
    ),
    SEVEN(
            7,
            0
    ),
    ELEVEN(
            11,
            2
    ),
    TWELVE(
            12,
            3
    ),
    THIRTEEN(
            13,
            4
    );

    private byte number;
    private byte value;

    CardValue(int number, int value) {
        this.number = (byte) number;
        this.value = (byte) value;
    }

    public byte getValue() {
        return value;
    }

    public byte getNumber() {
        return number;
    }
}
