public class Field {

    private static final int FIELD_SIZE = 3;

    private static final char DEFAULT_CELL_VALUE = ' ';

    private char[][] field = new char[FIELD_SIZE][FIELD_SIZE];

    private boolean proverkaVvoda(int cX, int cY, char m) {
        return cX < 0 | cX >= 3 | cY < 0 | cY >= 3 | !(m == 'x' | m == '0');
    }

    public boolean isFilled() {
        char znachenieMark=DEFAULT_CELL_VALUE;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == znachenieMark) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isFilledLine() {
        return isRowsFilled()||isColumnsFilled()||isDiagonalsFilled();
    }

    private boolean isRowsFilled() {
        char znachenieMark;
        for (int i = 0; i < 3; i++) {
            znachenieMark = field[i][i];
            for (int j = 0; j < 3; j++) {
                if (field[j][i] != znachenieMark) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isColumnsFilled() {

        char znachenieMark;

        for (int i = 0; i < 3; i++) {
            znachenieMark = field[i][i];
            for (int j = 0; j < 3; j++) {
                if (field[i][j] != znachenieMark) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isDiagonalsFilled() {

        char znachenieMark=field[1][1];
        int sum=0;
        for (int i = 0, j = 0; i < 3; i++, j++) {
            if (field[i][j] == znachenieMark) {
                sum++;
            }
            if (sum == 3) {
                return true;
            }
        }

        sum = 0;
        for (int i = 2, j = 0; j < 3; i--, j++) {
            if (field[i][j] == znachenieMark) {
                sum++;
            }
            if (sum == 3) {
                return true;
            }
        }
        return false;
    }


    public void setCell(int cellX, int cellY, char mark) {
        if (proverkaVvoda(cellX, cellY, mark)) {
            System.out.println("Для ввода допустимы лишь 'x' и '0', а также координаты должны быть в диапазоне 1-3 включительно! Начните заново!");
            eraseField();
            return;
        }
        if (mark == 'x') {
            field[cellX][cellY] = 'X';
        } else {
            field[cellX][cellY] = '0';
        }
        showField();
    }

    char getCell(int cellX, int cellY) {
        return field[cellX][cellY];
    }

    public void eraseField() {
        for (int i = 0; i < FIELD_SIZE; i++) {
            eraseLine(i);
        }
    }

    public void showField() {
        System.out.println();
        for (int i = 0; i < FIELD_SIZE; i++) {
            showLine(i);
            System.out.println();
        }
    }

    private void showLine(int lineNumber) {
        for (int i = 0; i < FIELD_SIZE; i++) {
            showCell(i,lineNumber);
        }
    }

    private void eraseLine(int lineNunber) {
        for (int i = 0; i < FIELD_SIZE; i++) {
            field[i][lineNunber] = DEFAULT_CELL_VALUE;
        }
    }

    private void showCell(int i, int i2) {
        System.out.print("[" + field[i][i2] + "]");
    }
}
