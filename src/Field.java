import java.util.ArrayList;

public class Field {
    Cell cell = new Cell();

    private static final int FIELD_SIZE = 3;

    private static final char DEFAULT_CELL_VALUE = ' ';

    private char[][] field = new char[FIELD_SIZE][FIELD_SIZE];

    private char lastInput =' ';

    private final char FIRST_PLAYER_MARK = 'x';


    char getLastInput() {
        return lastInput;
    }

    void setLastInput(char lastInput) {
        this.lastInput = lastInput;
    }

    private boolean proverkaVvoda(int cX, int cY) {
        return cX < 0 | cX >= FIELD_SIZE | cY < 0 | cY >= FIELD_SIZE;
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


    public void setCell(int cellX, int cellY, char mark) {
        if (proverkaVvoda(cellX, cellY)) {
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

    // Класс для хранения и возврата координат cellX и cellY выбранного лучшего поля при поиске очередного хода AI
    class Cell {
        int cellX;
        int cellY;
        Cell() {
            cellX=0;
            cellY=0;
        }

        Cell(int cellX, int cellY) {
            this.cellX = cellX;
            this.cellY = cellY;
        }
    }

    public Cell findLineWith2MarkAndReturnFreeCell(char mark) {
        //TODO улучшить, а то полный репит!!! Скорее всего выделить отдельную функцию, а то и больше.
        //проверка горизонтали
        int sum = 0;
        ArrayList<Cell> massCells = new ArrayList<>();
        Cell cellFree = new Cell();
        for (int j = 0; j < FIELD_SIZE; j++) {
            for (int i = 0; i < FIELD_SIZE; i++) {
                if (field[i][j] == ' ') {
                    cellFree.cellX = i;
                    cellFree.cellY = j;
                }

                if (field[i][j] == mark) {
                    sum++;
                }

                if (sum == FIELD_SIZE - 1) {
                    if (j == cellFree.cellY) {
                        massCells.add(cellFree);
                    } else {
                        cellFree.cellX++;
                        massCells.add(cellFree);
                    }
                }
            }
            sum=0;
        }

        //проверка вертикали
        sum = 0;
        cellFree.cellX=0;
        cellFree.cellY=0;
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
                        }
                    } else {
                        cellFree.cellY++;
                        boolean t = true;
                        for (Cell c : massCells) {
                            if ((c.cellX == cellFree.cellX) & (c.cellY == cellFree.cellY)) {
                                t = false;
                                break;
                            }
                        }
                        if (t) {
                            massCells.add(cellFree);
                        }
                    }
                }
            }
            sum=0;
        }

        //проверка нисходящей диагонали
        sum = 0;
        cellFree.cellX=0;
        cellFree.cellY=0;
        for (int i = 0, j = 0; i < FIELD_SIZE; i++, j++) {

            if (field[i][j] == ' ') {
                cellFree.cellX = i;
                cellFree.cellY = j;
            }

            if (field[i][j] == mark) {
                sum++;
            }

            if (sum == FIELD_SIZE - 1) {
                if (i != FIELD_SIZE -2) {
                    boolean t = true;
                    for (Cell c : massCells) {
                        if ((c.cellX == cellFree.cellX) & (c.cellY == cellFree.cellY)) {
                            t = false;
                            break;
                        }
                    }
                    if (t) {
                        massCells.add(cellFree);
                    }
                } else {
                    cellFree.cellX++;
                    cellFree.cellY++;
                    boolean t = true;
                    for (Cell c : massCells) {
                        if ((c.cellX == cellFree.cellX) & (c.cellY == cellFree.cellY)) {
                            t = false;
                            break;
                        }
                    }
                    if (t) {
                        massCells.add(cellFree);
                    }
                }
            }
        }


        //проверка восходящей диагонали
        sum = 0;
        cellFree.cellX=0;
        cellFree.cellY=0;
        for (int i = 2, j = 0; j < FIELD_SIZE; i--, j++) {

            if (field[i][j] == ' ') {
                cellFree.cellX = i;
                cellFree.cellY = j;
            }

            if (field[i][j] == mark) {
                sum++;
            }

            if (sum == FIELD_SIZE - 1) {
                if (i != FIELD_SIZE -2) {
                    boolean t = true;
                    for (Cell c : massCells) {
                        if ((c.cellX == cellFree.cellX) & (c.cellY == cellFree.cellY)) {
                            t = false;
                            break;
                        }
                    }
                    if (t) {
                        massCells.add(cellFree);
                    }
                } else {
                    cellFree.cellX--;
                    cellFree.cellY++;
                    boolean t = true;
                    for (Cell c : massCells) {
                        if ((c.cellX == cellFree.cellX) & (c.cellY == cellFree.cellY)) {
                            t = false;
                            break;
                        }
                    }
                    if (t) {
                        massCells.add(cellFree);
                    }
                }
            }
        }

        if (massCells.size() > 0) {
            return massCells.get((int) (Math.random() * massCells.size()));
        } else { return cell; }
    }

    public Cell findLineWith1MarkAndReturnRandomFreeCell(char mark) {

        //TODO findLineWith1MarkAndReturnRandomFreeCell улучшить, для начала обычный рандом по пустым ячейкам
        // возвращает рандомом координаты свободной клетки в линии, где уже 1 поле помечено, приоритет у поля, которое находится на пересечении двух таких линий
        // нужно чтобы метод при обнаружении чекал другие два поля и если они пустые
        // заносил их в возможные ходы, возможно стоит искать маркнутую ячейку, а потом проверять все
        // поля по диагонали, вертикали и горизонтали и если они равны ' ' то заносить из в массив.

        ArrayList<Cell> massCells = new ArrayList<>();
        Cell cellFree = new Cell();
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                if (field[i][j] == ' ') {
                    cellFree.cellX = i;
                    cellFree.cellY = j;
                    massCells.add(cellFree);
                }
            }
        }
        if (massCells.size() > 0) {
            return massCells.get((int) (Math.random() * massCells.size()));
        } else { return cell; }
    }

    public Cell randomFreeCell() {
        ArrayList<Cell> massCells = new ArrayList<>();
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                if (field[i][j] == ' ') {
                    Cell cellFree = new Cell();
                    cellFree.cellX = i;
                    cellFree.cellY = j;
                    System.out.println("CellFree: " + cellFree.cellX + " " + cellFree.cellY);
                    massCells.add(cellFree);
                }
            }
        }
        int a = (int) (Math.random() * massCells.size());
        System.out.println("Size = " + massCells.size() + " . Рандом: " + a);
        return massCells.get((int) (Math.random() * massCells.size()));
    }
}