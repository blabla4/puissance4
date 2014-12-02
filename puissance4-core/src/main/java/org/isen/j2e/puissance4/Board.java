package org.isen.j2e.puissance4;

public class Board {
    private final int ROW_NUMBER = 6;
    private final int COL_NUMBER = 7;
    private Coin[][] grid = new Coin[ROW_NUMBER][COL_NUMBER];

    public Board() {
        for (int i = 0; i < ROW_NUMBER; i++) {
            for (int j = 0; j < COL_NUMBER; j++) {
                grid[i][j] = null;
            }
        }
    }

    public String getBoardAsText() {
        String text = "";
        text += "<br>";
        for (int i = 0; i < ROW_NUMBER; i++) {
            for (int j = 0; j < COL_NUMBER; j++) {
                if (grid[i][j] == Coin.BLUE) {
                    text += "| X ";
                } else if (grid[i][j] == Coin.RED) {
                    text += "| O ";
                } else {
                    text += "| _ ";
                }
            }
            text += "|<br>";
        }
        return text;
    }

    public void display() {
        System.out.print("\n");
        for (int i = 0; i < ROW_NUMBER; i++) {
            for (int j = 0; j < COL_NUMBER; j++) {
                if (grid[i][j] == Coin.BLUE) {
                    System.out.print("| X ");
                } else if (grid[i][j] == Coin.RED) {
                    System.out.print("| O ");
                } else {
                    System.out.print("|   ");
                }
            }
            System.out.print("|\n");
        }
    }

    public Coin getCoin(int col, int row) {
        Coin coin;
        try {
            coin = grid[row - 1][col - 1];
        } catch (ArrayIndexOutOfBoundsException e) {
            coin = null;
        }
        return coin;
    }

    public int setCoin(Coin coin, int col, int row) throws GameException {
        if(col < 1 || col > COL_NUMBER) {
            throw new NullColumnException("La colonne " + col + " n'exsite pas");
        }
        if (!isFull()) {
            if (row - 1 < 0) {
                throw new ColumnFullException("La colonne " + col + " est pleine");
            }
            if (grid[row - 1][col - 1] != null) {
                return setCoin(coin, col, row - 1);
            } else {
                grid[row - 1][col - 1] = coin;
                return row;
            }
        } else {
            throw new BoardFullException("La grille est pleine");
        }
    }

    public int getRowNumber() {
        return ROW_NUMBER;
    }

    public int getColNumber() {
        return COL_NUMBER;
    }

    public boolean isFull() {
        boolean isFull = true;
        for (int i = 0; i < ROW_NUMBER; i++) {
            for (int j = 0; j < COL_NUMBER; j++) {
                if (grid[i][j] == null) {
                    isFull = false;
                }
            }
        }
        return isFull;
    }
}
