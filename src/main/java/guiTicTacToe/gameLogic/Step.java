package guiTicTacToe.gameLogic;


import static guiTicTacToe.gameLogic.GameTable.*;

public abstract class Step {
    private static final char EMPTY = '\u0000';

    private final char gamerSymbol;

    public Step(char gamerSymbol) {
        this.gamerSymbol = gamerSymbol;
    }

    // проверка, является ли ячейка свободной
    public boolean isCellFree(int index) {
        return isCellFree(calculateRow(index), calculateCol(index));
    }
    public boolean isCellFree(int row, int col) {
        if (TABLE[row][col] == EMPTY) {
            return true;
        }
        return false;
    }

    // вычисление строки таблицы по номеру ячейки
    public int calculateRow(int index) {
        return index / 3 - (index % 3 == 0 ? 1 : 0);
    }

    // вычисление столбца таблицы по номеру ячейки
    public int calculateCol(int index) {
        int temp = index % 3;
        return temp == 0 ? 2 : temp - 1;
    }

    // добавить ход в таблицу
    public void makeTurn(int index, char ch) {
        makeTurn(calculateRow(index), calculateCol(index), ch);
    }

    public void makeTurn(int row, int col, char ch) {
        TABLE[row][col] = ch;
    }

    public char getGamerSymbol() {
        return gamerSymbol;
    }
}
