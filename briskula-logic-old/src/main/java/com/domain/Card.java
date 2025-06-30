package com.domain;

@Deprecated
public record Card ( //TODO: Remove this class and use Card from italian-cards module
    CardType type,
    CardValue value
){
    public boolean isTrumpCard(CardType trumpCardType) {
        return this.type().equals(trumpCardType);
    }

    @Override
    public String toString() {
        return String.format("%s %s", value.getNumber(), type.getName());
    }

    public String toCroatianString() {
        return String.format("%d %s", value.getNumber(), type.getCroatianName());
    }

    public Card compareTo(Card card, CardType trumpCardType) {
        var isCard1Trump = this.isTrumpCard(trumpCardType);
        var isCard2Trump = card.isTrumpCard(trumpCardType);
        var card1Value = this.value().getValue();
        var card2Value = card.value().getValue();
        var card1Number = this.value().getNumber();
        var card2Number = card.value().getNumber();

        if (isCard1Trump) {
            return this;
        } else if (isCard2Trump) {
            return card;
        } else {
            if (card1Value == card2Value) {
                return card1Number > card2Number ? this : card;
            }
            return card1Value > card2Value ? this : card;
        }
    }
}
