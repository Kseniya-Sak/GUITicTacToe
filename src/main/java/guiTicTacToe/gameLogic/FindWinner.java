package guiTicTacToe.gameLogic;

import static guiTicTacToe.gameLogic.GameTable.TABLE;

public class FindWinner {
    private static final char EMPTY = '\u0000';

    public static char getWinner() {
        char winner = getWinnerByRows();
        if (winner != EMPTY) {
            return winner;
        }

        winner = getWinnerByCols();
        if (winner != EMPTY) {
            return winner;
        }

        winner = getWinnerByPrincipalDiagonal();
        if (winner != EMPTY) {
            return winner;
        }

        return getWinnerBySecondaryDiagonal();
    }

    private static char getWinnerByRows() {
        for (int i = 0; i < 3; i++) {
            if (TABLE[i][0] == TABLE[i][1] && TABLE[i][1] == TABLE[i][2] && TABLE[i][0] != EMPTY) {
                return TABLE[i][0];
            }
        }
        return EMPTY;
    }

    private static char getWinnerByCols() {
        for (int i = 0; i < 3; i++) {
            if (TABLE[0][i] == TABLE[1][i] && TABLE[1][i] == TABLE[2][i] && TABLE[0][i] != EMPTY) {
                return TABLE[0][i];
            }
        }
        return EMPTY;
    }

    private static char getWinnerByPrincipalDiagonal() {
        if (TABLE[0][0] == TABLE[1][1] && TABLE[1][1] == TABLE[2][2] && TABLE[0][0] != EMPTY) {
            return TABLE[0][0];
        }
        return EMPTY;
    }

    private static char getWinnerBySecondaryDiagonal() {
        if (TABLE[0][2] == TABLE[1][1] && TABLE[1][1] == TABLE[2][0] && TABLE[0][2] != EMPTY ) {
            return TABLE[0][2];
        }
        return EMPTY;
    }

    public static boolean isDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (TABLE[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
}
