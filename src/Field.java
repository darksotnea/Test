public class Field {

    private static final int FIELD_SIZE = 3;

    private static final char DEFAULT_CELL_VALUE = ' ';

    private char[][] field = new char[FIELD_SIZE][FIELD_SIZE];

    private char lastInput =' ';

    private final char FIRST_PLAYER_MARK = 'x';

    private final char SECOND_PLAYER_MARK = '0';


    char getLastInput() {
        return lastInput;
    }

    void setLastInput(char lastInput) {
        this.lastInput = lastInput;
    }

    private boolean proverkaVvoda(int cX, int cY, char m) {
        return cX < 0 | cX >= 3 | cY < 0 | cY >= 3; // | !(m == FIRST_PLAYER_MARK | m == SECOND_PLAYER_MARK);
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

        int sum = 0;

        for (int j = 0; j < 3; j++) {
            znachenieMark = field[j][j];
            for (int i = 0; i < 3; i++) {
                if ((field[i][j] == znachenieMark) & (field[i][j] != DEFAULT_CELL_VALUE)) {
                    sum++;
                } else { break; }

                if (sum == 3) {
                    return true;
                }
            }
            sum=0;
        }
        return false;
    }

    private boolean isColumnsFilled() {

        char znachenieMark;

        int sum=0;

        for (int i = 0; i < 3; i++) {
            znachenieMark = field[i][i];
            for (int j = 0; j < 3; j++) {
                if ((field[i][j] == znachenieMark) & (field[i][j] != DEFAULT_CELL_VALUE)) {
                    sum++;
                } else { break; }

                if (sum == 3) {
                    return true;
                }
            }
            sum=0;
        }
        return false;
    }

    private boolean isDiagonalsFilled() {

        char znachenieMark=field[1][1];
        int sum=0;
        for (int i = 0, j = 0; i < 3; i++, j++) {
            if ((field[i][j] == znachenieMark) & (field[i][j] != DEFAULT_CELL_VALUE)) {
                sum++;
            } else { break; }
            if (sum == 3) {
                return true;
            }
        }

        sum = 0;
        for (int i = 2, j = 0; j < 3; i--, j++) {
            if ((field[i][j] == znachenieMark) & (field[i][j] != DEFAULT_CELL_VALUE)) {
                sum++;
            } else { break; }
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
        if (field[cellX][cellY] != DEFAULT_CELL_VALUE) {
            System.out.println("Это поле уже помечено, введите другие координаты.");
            showField();
            return;
        }

        if (lastInput != DEFAULT_CELL_VALUE) {
            if (lastInput == mark) {
                System.out.println("Этот игрок уже ходил, сейчас должен ходить другой игрок.");
                showField();
                return;
            }
        } else {
            lastInput = mark;}

        if (mark == FIRST_PLAYER_MARK) {
            field[cellX][cellY] = 'X';
        } else {
            field[cellX][cellY] = '0';
        }
        lastInput = mark;
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
