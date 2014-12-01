package org.isen.j2e.puissance4;

import static org.junit.Assert.*;

import org.junit.Test;


public class UnitTests
{
    @Test
    public void verify_game_won_d_2() throws Exception {
        Game game = new Game();
        game.play(Coin.BLUE, 4);
        game.play(Coin.RED, 3);
        game.play(Coin.RED, 2);
        game.play(Coin.RED, 2);
        game.play(Coin.RED, 1);
        game.play(Coin.RED, 1);
        game.play(Coin.RED, 1);
        game.play(Coin.BLUE, 3);
        game.play(Coin.BLUE, 2);
        game.play(Coin.BLUE, 1);
        assertEquals(game.getWinner(), Coin.BLUE);
    }

    @Test
    public void verify_game_won_d() throws Exception {
        Game game = new Game();
        game.play(Coin.BLUE, 1);
        game.play(Coin.RED, 2);
        game.play(Coin.RED, 3);
        game.play(Coin.RED, 3);
        game.play(Coin.RED, 4);
        game.play(Coin.RED, 4);
        game.play(Coin.RED, 4);
        game.play(Coin.BLUE, 2);
        game.play(Coin.BLUE, 3);
        game.play(Coin.BLUE, 4);
        assertEquals(game.getWinner(), Coin.BLUE);
    }

    @Test
    public void verify_game_won_h() throws Exception {
        Game game = new Game();
        game.play(Coin.BLUE, 1);
        game.play(Coin.BLUE, 1);
        game.play(Coin.BLUE, 1);
        game.play(Coin.BLUE, 1);
        assertEquals(game.getWinner(), Coin.BLUE);
    }

    @Test
    public void verify_game_won_v() throws Exception {
        Game game = new Game();
        game.play(Coin.BLUE, 1);
        game.play(Coin.BLUE, 2);
        game.play(Coin.BLUE, 3);
        game.play(Coin.BLUE, 4);
        assertEquals(game.getWinner(), Coin.BLUE);
    }

    @Test
    public void verify_game_won_v_mix() throws Exception {
        Game game = new Game();
        game.play(Coin.RED, 1);
        game.play(Coin.RED, 2);
        game.play(Coin.BLUE, 3);
        game.play(Coin.BLUE, 4);
        game.play(Coin.BLUE, 5);
        game.play(Coin.BLUE, 6);
        assertEquals(game.getWinner(), Coin.BLUE);
    }

    @Test(expected = ColumnFullException.class)
    public void verify_column_full() throws Exception {
        Game game = new Game();
        game.play(Coin.BLUE, 1);
        game.play(Coin.BLUE, 1);
        game.play(Coin.BLUE, 1);
        game.play(Coin.BLUE, 1);
        game.play(Coin.BLUE, 1);
        game.play(Coin.BLUE, 1);
        game.play(Coin.BLUE, 1);
    }

    @Test(expected = BoardFullException.class)
    public void verify_board_full() throws Exception {
        Game game = new Game();
        for(int i = 0; i<7; i++) {
            game.play(Coin.BLUE, i + 1);
            game.play(Coin.BLUE, i + 1);
            game.play(Coin.BLUE, i + 1);
            game.play(Coin.BLUE, i + 1);
            game.play(Coin.BLUE, i + 1);
            game.play(Coin.BLUE, i + 1);
        }
        game.play(Coin.BLUE, 7);
    }
}
