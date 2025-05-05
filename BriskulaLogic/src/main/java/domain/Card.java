package domain;

public record Card (
    CardType type,
    CardValue value
){
    public boolean isTrumpCard(CardType trumpCardType) {
        return this.type().equals(trumpCardType);
    }
}
