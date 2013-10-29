import java.util.ArrayList;

public class Field {
    Cell cell = new Cell();

    private static final int FIELD_SIZE = 3;

    private static final char DEFAULT_CELL_VALUE = ' ';

    private char[][] field = new char[FIELD_SIZE][FIELD_SIZE];

    private char lastInput =' ';

    private final char FIRST_PLAYER_MARK = 'x';

    ArrayList<Cell> lastCells = new ArrayList();

    char getLastInput() {
        return lastInput;
    }

    void setLastInput(char lastInput) {
        this.lastInput = lastInput;
    }

    char getLastInputH() {
        if (lastInput == FIRST_PLAYER_MARK) {
            return 'X';
        }
        return '0';
    }

     public boolean isFilled() {
        char znachenieMark=DEFAULT_CELL_VALUE;
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
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

        for (int j = 0; j < FIELD_SIZE; j++) {
            znachenieMark = field[j][j];
            for (int i = 0; i < FIELD_SIZE; i++) {
                if ((field[i][j] == znachenieMark) & (field[i][j] != DEFAULT_CELL_VALUE)) {
                    sum++;
                } else { break; }

                if (sum == FIELD_SIZE) {
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

        for (int i = 0; i < FIELD_SIZE; i++) {
            znachenieMark = field[i][i];
            for (int j = 0; j < FIELD_SIZE; j++) {
                if ((field[i][j] == znachenieMark) & (field[i][j] != DEFAULT_CELL_VALUE)) {
                    sum++;
                } else { break; }

                if (sum == FIELD_SIZE) {
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
        for (int i = 0, j = 0; i < FIELD_SIZE; i++, j++) {
            if ((field[i][j] == znachenieMark) & (field[i][j] != DEFAULT_CELL_VALUE)) {
                sum++;
            } else { break; }
            if (sum == FIELD_SIZE) {
                return true;
            }
        }

        sum = 0;
        for (int i = FIELD_SIZE - 1, j = 0; j < FIELD_SIZE; i--, j++) {
            if ((field[i][j] == znachenieMark) & (field[i][j] != DEFAULT_CELL_VALUE)) {
                sum++;
            } else { break; }
            if (sum == FIELD_SIZE) {
                return true;
            }
        }
        return false;
    }

    public void reverseLastInputMark() {
        if (lastInput == FIRST_PLAYER_MARK) {
             lastInput = '0';
        } else if (lastInput == '0') {
            lastInput = 'x';
        }
    }

    public void setCell(int cellX, int cellY, char mark) {
        if (cellX < 0 | cellX >= FIELD_SIZE | cellY < 0 | cellY >= FIELD_SIZE) {
            System.out.println("Для ввода допустимы лишь 'x' и '0', а также координаты должны быть в диапазоне 1-3 включительно! Начните заново!");
            eraseField();
            return;
        }

        if ((field[cellX][cellY] != DEFAULT_CELL_VALUE) & mark != ' ') {
            System.out.println("Это поле уже помечено, введите другие координаты.");
            showField();
            return;
        }

        if (lastInput != DEFAULT_CELL_VALUE){
            if (lastInput == mark) {
                System.out.println("Этот игрок уже ходил, сейчас должен ходить другой игрок.");
                showField();
                return;
            }
        }

        if (mark == FIRST_PLAYER_MARK) {
            field[cellX][cellY] = 'X';
        } else if (mark == '0') {
            field[cellX][cellY] = '0';
        }

        if (mark != ' ') {
            lastInput = mark;
            lastCells.add(new Cell(cellX, cellY, lastInput));
        } else {
            field[cellX][cellY] = ' ';
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

    // Класс Cell для хранения и возврата отметки(mark) и координат cellX, cellY выбранного лучшего поля при поиске очередного хода AI.
    class Cell {
        int cellX;
        int cellY;
        char mark;
        Cell() {
            cellX=-1;
            cellY=-1;
        }

        Cell(int x, int y, char lastMark) {
            cellX=x;
            cellY=y;
            mark = lastMark;
        }
    }

    public Cell findLineWith2MarkAndReturnFreeCell(char mark) {
        //TODO улучшить, а то полный репит!!! Скорее всего выделить отдельную функцию, а то и больше.
        int sum = 0;
        ArrayList<Cell> massCells = new ArrayList<>();
        Cell cellFree = new Cell();

        //проверка горизонталей
        for (int j = 0; j < FIELD_SIZE; j++) {
            for (int i = 0; i < FIELD_SIZE; i++) {
                if (field[i][j] == ' ') {
                    cellFree.cellX = i;
                    cellFree.cellY = j;
                }

                if (field[i][j] == mark) {
                    sum++;
                }

                if (sum == 2) {
                    if (j == cellFree.cellY) {
                        boolean t = true;
                        for (Cell c : massCells) {
                            if ((c.cellX == cellFree.cellX) & (c.cellY == cellFree.cellY)) {
                                t = false;
                                break;
                            }
                        }
                        if (t) {
                            massCells.add(cellFree);
                            cellFree = new Cell();
                        }
                    } else if ((j == 1) && (i != 2) &&(field[i +1][j] == ' ')) {
                        cellFree.cellX = i + 1;
                        boolean t = true;
                        for (Cell c : massCells) {
                            if ((c.cellX == cellFree.cellX) & (c.cellY == cellFree.cellY)) {
                                t = false;
                                break;
                            }
                        }
                        if (t) {
                            massCells.add(cellFree);
                            cellFree = new Cell();
                        }
                        i++;
                    }
                }
            }
            sum=0;
        }

        //проверка вертикалей
        sum = 0;
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                if (field[i][j] == ' ') {
                    cellFree.cellX = i;
                    cellFree.cellY = j;
                }

                if (field[i][j] == mark) {
                    sum++;
                }

                if (sum == FIELD_SIZE - 1) {
                    if (i == cellFree.cellX) {
                        boolean t = true;
                        for (Cell c : massCells) {
                            if ((c.cellX == cellFree.cellX) & (c.cellY == cellFree.cellY)) {
                                t = false;
                                break;
                            }
                        }
                        if (t) {
                            massCells.add(cellFree);
                            cellFree = new Cell();
                        }
                    } else if ((i == 1) && (j!=2) && (field[i][j + 1] == ' ')) {
                        cellFree.cellY = j + 1;
                        boolean t = true;
                        for (Cell c : massCells) {
                            if ((c.cellX == cellFree.cellX) & (c.cellY == cellFree.cellY)) {
                                t = false;
                                break;
                            }
                        }
                        if (t) {
                            massCells.add(cellFree);
                            cellFree = new Cell();
                        }
                        j++;
                    }
                }
            }
            sum=0;
        }

        //проверка нисходящей диагонали
        sum = 0;
        for (int i = 0, j = 0; i < FIELD_SIZE; i++, j++) {

            if (field[i][j] == ' ') {
                cellFree.cellX = i;
                cellFree.cellY = j;
            }

            if (field[i][j] == mark) {
                sum++;
            }

            if (sum == 2) {
                if ((i != 1) && ((cellFree.cellX == i-1 & cellFree.cellY == j-1) | (cellFree.cellX == i-2 & cellFree.cellY == j-2))) {
                    boolean t = true;
                    for (Cell c : massCells) {
                        if ((c.cellX == cellFree.cellX) & (c.cellY == cellFree.cellY)) {
                            t = false;
                            break;
                        }
                    }
                    if (t) {
                        massCells.add(cellFree);
                        cellFree = new Cell();
                    }
                } else if ((i == 1) && (field[i+1][j+1] == ' ')) {
                    cellFree.cellX = i + 1;
                    cellFree.cellY = j + 1;
                    boolean t = true;
                    for (Cell c : massCells) {
                        if ((c.cellX == cellFree.cellX) & (c.cellY == cellFree.cellY)) {
                            t = false;
                            break;
                        }
                    }
                    if (t) {
                        massCells.add(cellFree);
                        cellFree = new Cell();
                    }
                    i++;
                    j++;
                }
            }
        }

        //проверка восходящей диагонали
        sum = 0;
        for (int i = 2, j = 0; j < FIELD_SIZE; i--, j++) {

            if (field[i][j] == ' ') {
                cellFree.cellX = i;
                cellFree.cellY = j;
            }

            if (field[i][j] == mark) {
                sum++;
            }

            if (sum == 2) {
                if ((i != 1) && ((cellFree.cellX == i+1 & cellFree.cellY == j-1) | (cellFree.cellX == i+2 & cellFree.cellY == j-2))) {
                    boolean t = true;
                    for (Cell c : massCells) {
                        if ((c.cellX == cellFree.cellX) & (c.cellY == cellFree.cellY)) {
                            t = false;
                            break;
                        }
                    }
                    if (t) {
                        massCells.add(cellFree);
                        cellFree = new Cell();
                    }
                } else if ((i == 1) && (field[i-1][j+1] == ' ')) {
                    cellFree.cellX = i - 1;
                    cellFree.cellY = j + 1;
                    boolean t = true;
                    for (Cell c : massCells) {
                        if ((c.cellX == cellFree.cellX) & (c.cellY == cellFree.cellY)) {
                            t = false;
                            break;
                        }
                    }
                    if (t) {
                        massCells.add(cellFree);
                        cellFree = new Cell();
                    }
                    i--;
                    j++;
                }
            }
        }

        if (massCells.size() > 0) {
            return massCells.get((int) (Math.random() * massCells.size()));
        } else { return cell; }
    }

    public Cell findLineWith1MarkAndReturnRandomFreeCell(char mark) {
        //TODO возвращает рандомом координаты свободной клетки в линии, где уже 1 поле помечено, приоритет у поля, которое находится на пересечении двух таких линий

        int sum = 0;
        ArrayList<Cell> massCells = new ArrayList<>();
        Cell cellFree = new Cell();
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                if (field[i][j] == ' ') {
                    cellFree.cellX = i;
                    cellFree.cellY = j;
                }

                if (field[i][j] == mark) {
                    sum++;
                }

                if (sum == FIELD_SIZE - 1) {
                    massCells.add(cellFree);
                    cellFree = new Cell();
                }
            }
            sum=0;
        }
        if (massCells.size() > 0) {
            return massCells.get((int) (Math.random() * massCells.size()));
        } else { return cell; }
    }

    public Cell randomFreeCell() {
        ArrayList<Cell> massCells = new ArrayList<>();
        Cell cellFree = new Cell();
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                if (field[i][j] == ' ') {
                    cellFree.cellX = i;
                    cellFree.cellY = j;
                    massCells.add(cellFree);
                    cellFree = new Cell();
                }
            }
        }
        Cell cellTest1 = massCells.get((int) (Math.random() * massCells.size()));
        return cellTest1;
    }
}