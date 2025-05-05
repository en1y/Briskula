package domain;

public record Card (
    CardType type,
    CardValue value
){
    public boolean isTrumpCard(CardType trumpCardType) {
        return this.type().equals(trumpCardType);
    }

    @Override
    public String toString() {
        return String.format("%s %s", value.getName(), type.getName());
    }

    public String toCroatianString() {
        return String.format("%s %s", value.getCroatianName(), type.getCroatianName());
    }

}
