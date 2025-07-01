package com.template.cards.domain;

import com.italian.cards.ItalianCard;
import base.game.model.AbstractPlayer;

public class BriskulaPlayer extends AbstractPlayer<ItalianCard> {

    private int points;
    private final int id;

    public BriskulaPlayer(int id, String name) {
        super(name);
        this.id = id;
        points = 0;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public int getPoints() {
        return points;
    }

    public int getId() {
        return id;
    }
}
