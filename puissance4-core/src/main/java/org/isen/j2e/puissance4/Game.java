package org.isen.j2e.puissance4;

public class Game {
    private Board board;
    private final int COIN_IN_A_ROW_NUMBER = 4;

    private int lastCoinRow;
    private int lastCoinColumn;
    private Coin lastCoin;

    public Game() {
        board = new Board();
    }

    public void play(Coin coin, int col) throws GameException {
        lastCoinRow = board.setCoin(coin, col, board.getRowNumber());
        lastCoinColumn = col;
        lastCoin = coin;
    }

    public Coin getWinner() {
        int count = 0;
        for (int i = 0; i < board.getRowNumber(); i++) {
            if (board.getCoin(lastCoinColumn, i + 1) == lastCoin) {
                count++;
                if (count == COIN_IN_A_ROW_NUMBER) {
                    return lastCoin;
                }
            } else {
                count = 0;
            }
        }
        count = 0;
        for (int i = 0; i < board.getColNumber(); i++) {
            if (board.getCoin(i + 1, lastCoinRow) == lastCoin) {
                count++;
                if (count == COIN_IN_A_ROW_NUMBER) {
                    return lastCoin;
                }
            } else {
                count = 0;
            }
        }

        count = 0;
        for (int i = 0; i < board.getRowNumber() * 2; i++) {
            if (board.getCoin(lastCoinColumn - board.getRowNumber() + i, lastCoinRow + board.getRowNumber() - i) == lastCoin) {
                count++;
                if (count == COIN_IN_A_ROW_NUMBER) {
                    return lastCoin;
                }
            } else {
                count = 0;
            }
        }

        count = 0;
        for (int i = 0; i < board.getRowNumber() * 2; i++) {
            if (board.getCoin(lastCoinColumn - board.getRowNumber() + i, lastCoinRow - board.getRowNumber() + i) == lastCoin) {
                count++;
                if (count == COIN_IN_A_ROW_NUMBER) {
                    return lastCoin;
                }
            } else {
                count = 0;
            }
        }

        return null;
    }

    public Board getBoard() {
        return board;
    }
}
