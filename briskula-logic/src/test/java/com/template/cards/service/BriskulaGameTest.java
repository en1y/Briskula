package com.template.cards.service;

import org.junit.Test;

import static org.junit.Assert.*;


public class BriskulaGameTest {

    @Test
    public void cannotCreateGameWithInvalidPlayersNum() {
        assertThrows(IllegalArgumentException.class, () -> {
            new BriskulaGame(-1, 3);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new BriskulaGame(0, 3);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new BriskulaGame(1, 3);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new BriskulaGame(5, 3);
        });

    }

    @Test
    public void testCardNumberWith2Players(){
        var game = new BriskulaGame(2, 3);
        assertEquals(40, game.getDeck().getSize());
    }

    @Test
    public void testCardNumberWith3Players(){
        var game = new BriskulaGame(3, 3);
        assertEquals(39, game.getDeck().getSize());
    }

    @Test
    public void testCardNumberWith4Players(){
        var game = new BriskulaGame(4, 3);
        assertEquals(40, game.getDeck().getSize());
    }

}
