public abstract class Card implements Comparable<Card> {
    private final CardType type;
    private final CardValue value;

    public Card(CardType type, CardValue value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public int compareTo(Card o) {
        throw new UnsupportedOperationException("This method has to be implemented");
    }

    public CardType getType() {
        return type;
    }

    public CardValue getValue() {
        return value;
    }
}
