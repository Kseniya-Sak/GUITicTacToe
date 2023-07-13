package guiTicTacToe.gameLogic;

import java.util.Random;

import static guiTicTacToe.gameLogic.GameTable.TABLE;

public class ComputerStep extends Step {
    public ComputerStep(char gamerSymbol) {
        super(gamerSymbol);
    }

    public void makeComputerTurn() {
        if (tryWin(getGamerSymbol())) {
            return;
        }
        if (tryWin('X')) {
            return;
        }
        if (tryWinOnNextTurn()) {
            return;
        }
        makeRandomComputerGame();
    }

    // проверяет, где нехватает одного хода до победы
    private boolean tryWin(char checkCell) {
        return tryWinSetToRow(checkCell) || tryWinSetToCol(checkCell) ||
                tryWinSetToPrincipalDiagonal(checkCell) || tryWinSetToSecondaryDiagonal(checkCell);
    }

    private boolean tryWinSetToRow(char checkCell) {
        for (int i = 0; i < TABLE.length; i++) {
            int res = 0;
            int emptyI = -1;
            int emptyJ = -1;
            for (int j = 0; j < TABLE[i].length; j++) {
                if (TABLE[i][j] == checkCell) {
                    res++;
                }
                if (isCellFree(i, j)) {
                    emptyI = i;
                    emptyJ = j;
                }
            }
            if (handleTryWinSetResult(res, emptyI, emptyJ)) {
                return true;
            }
        }
        return false;
    }

    private boolean tryWinSetToCol(char checkCell) {
        for (int j = 0; j < TABLE.length; j++) {
            int res = 0;
            int emptyI = -1;
            int emptyJ = -1;
            for (int i = 0; i < TABLE.length; i++) {
                if (TABLE[i][j] == checkCell) {
                    res++;
                }
                if (isCellFree(i, j)) {
                    emptyI = i;
                    emptyJ = j;
                }
            }
            if (handleTryWinSetResult(res, emptyI, emptyJ)) {
                return true;
            }
        }
        return false;
    }

    private boolean tryWinSetToPrincipalDiagonal(char checkCell) {
        int res = 0;
        int emptyI = -1;
        int emptyJ = -1;
        for (int i = 0; i < TABLE.length; i++) {
            if (TABLE[i][i] == checkCell) {
                res++;
            }
            if (isCellFree(i, i)) {
                emptyI = i;
                emptyJ = i;
            }
        }
        return handleTryWinSetResult(res, emptyI, emptyJ);
    }

    private boolean tryWinSetToSecondaryDiagonal(char checkCell) {
        int res = 0;
        int emptyI = -1;
        int emptyJ = -1;
        for (int i = 0; i < TABLE.length; i++) {
            int j = TABLE.length - 1 - i;
            if (TABLE[i][j] == checkCell) {
                res++;
            }
            if (isCellFree(i, j)) {
                emptyI = i;
                emptyJ = j;
            }
        }
        return handleTryWinSetResult(res, emptyI, emptyJ);
    }
    private boolean handleTryWinSetResult(int res, int emptyI, int emptyJ) {
        if (res == 2 && emptyI > -1 && emptyJ > -1) {
            makeTurn(emptyI, emptyJ, getGamerSymbol());
            return true;
        }
        return false;
    }

    // находит заполненную ячейку и рандомно проверяет, можно ли заполнить ячейку, расположенную рядом
    // если да, то заполняет
    public boolean tryWinOnNextTurn() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (TABLE[i][j] == getGamerSymbol()) {
                    int[][] variants = getVariants(i, j);
                    int rowIndex = new Random().nextInt(variants.length);
                    for (int k = 0; k < variants.length; k++) {
                        if (isCellFree(variants[rowIndex][0], variants[rowIndex][1])) {
                            makeTurn(variants[rowIndex][0], variants[rowIndex][1], getGamerSymbol());
                            return true;
                        }
                        rowIndex++;
                        if (rowIndex >= variants.length) {
                            rowIndex = 0;
                        }
                    }
                }
            }
        }
        return false;
    }

    // все варианты рядом расположеных ячеек
    public static int[][] getVariants(int i, int j) {
        if (i == 0) {
            if (j == 0) {
                return new int[][] { { 0, 1 }, { 1, 1 }, { 1, 0 } };
            } else if (j == 1) {
                return new int[][] { { 0, 2 }, { 1, 1 }, { 0, 0 } };
            } else {
                return new int[][] { { 1, 2 }, { 1, 1 }, { 0, 1 } };
            }
        } else if (i == 1) {
            if (j == 0) {
                return new int[][] { { 0, 0 }, { 1, 1 }, { 2, 0 } };
            } else if (j == 1) {
                return new int[][] { { 0, 1 }, { 0, 2 }, { 1, 2 }, { 2, 2 }, { 2, 1 }, { 2, 0 }, { 1, 0 }, { 0, 0 } };
            } else {
                return new int[][] { { 2, 2 }, { 1, 1 }, { 0, 2 } };
            }
        } else {
            if (j == 0) {
                return new int[][] { { 1, 0 }, { 1, 1 }, { 2, 1 } };
            } else if (j == 1) {
                return new int[][] { { 1, 1 }, { 2, 2 }, { 2, 0 } };
            } else {
                return new int[][] { { 1, 2 }, { 2, 1 }, { 1, 1 } };
            }
        }
    }

    // слуайный ход компьютера
    private void makeRandomComputerGame() {
        int[] freeCells = new int[9];
        int count = 0;
        for (int i = 0; i < TABLE.length; i++) {
            for (int j = 0; j < TABLE[i].length; j++) {
                if (isCellFree(i, j)) {
                    freeCells[count++] = i * 3 + j + 1;
                }
            }
        }
        int index = new Random().nextInt(count);
        makeTurn(freeCells[index], getGamerSymbol());
    }
}
