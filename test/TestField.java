import org.junit.Test;
import static junit.framework.Assert.*;

public class TestField {
    private final char ZNACHENIE_POLYA_0 = '0';
    private final char ZNACHENIE_POLYA_X = 'x';

    @Test
    public void testWhatFilled() {
        Field field = new Field();
        field.eraseField();
        int rand = (int)(Math.random()*2);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (rand == 0) {
                    field.setCell(i, j, ZNACHENIE_POLYA_0);
                    field.setLastInput(ZNACHENIE_POLYA_X);
                }else {
                    field.setCell(i, j, ZNACHENIE_POLYA_X);
                    field.setLastInput(ZNACHENIE_POLYA_0);
                }
            }
        }
        assertEquals("Проверка на заполненность массива.",true,field.isFilled());
    }

    @Test
    public void testWhatNotFilled() {
        Field field = new Field();
        field.eraseField();
        int rand = (int)(Math.random()*2);
        int a = (int)(Math.random()*3);
        int b = (int)(Math.random()*3);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (a == i & b == j) {

                } else {
                    if (rand == 0) {
                        field.setCell(i, j, ZNACHENIE_POLYA_0);
                        field.setLastInput(ZNACHENIE_POLYA_X);
                    } else {
                        field.setCell(i, j, ZNACHENIE_POLYA_X);
                        field.setLastInput(ZNACHENIE_POLYA_0);
                    }
                }
            }
        }
        field.showField();

        assertEquals("Проверка на не заполненность массива.",false,field.isFilled());
    }

    @Test
    public void testWhatFilledLineColumns() {
        Field field = new Field();
        field.eraseField();
        int rand = (int)(Math.random()*2);
        int i = (int)(Math.random()*3);
        for (int j = 0; j < 3; j++) {
            if (rand == 0) {
                field.setCell(i, j, ZNACHENIE_POLYA_0);
                field.setLastInput(ZNACHENIE_POLYA_X);
            }else {
                field.setCell(i, j, ZNACHENIE_POLYA_X);
                field.setLastInput(ZNACHENIE_POLYA_0);
            }
        }
        assertEquals("Проверка на заполнение столбца.",true,field.isFilledLine());
    }

    @Test
    public void testWhatFilledLineRows() {
        Field field = new Field();
        field.eraseField();
        int rand = (int)(Math.random()*2);
        int j = (int)(Math.random()*3);
        for (int i = 0; i < 3; i++) {
            if (rand == 0) {
                field.setCell(i, j, ZNACHENIE_POLYA_0);
                field.setLastInput(ZNACHENIE_POLYA_X);
            }else {
                field.setCell(i, j, ZNACHENIE_POLYA_X);
                field.setLastInput(ZNACHENIE_POLYA_0);
            }
        }
        assertEquals("Проверка на заполнение строки.",true,field.isFilledLine());
    }

    @Test
    public void testWhatFilledLineDiagonals() {
        Field field = new Field();
        field.eraseField();
        int rand = (int)(Math.random()*2);
        for (int i = 2, j = 0; j < 3; i--, j++) {
            if (rand == 0) {
                field.setCell(i, j, ZNACHENIE_POLYA_0);
                field.setLastInput(ZNACHENIE_POLYA_X);
            }else {
                field.setCell(i, j, ZNACHENIE_POLYA_X);
                field.setLastInput(ZNACHENIE_POLYA_0);
            }
        }
        assertEquals("Проверка на заполнение восходящей диагонали.",true,field.isFilledLine());

        field.eraseField();
        for (int i = 0, j = 0; j < 3; i++, j++) {
            if (rand == 0) {
                field.setCell(i, j, ZNACHENIE_POLYA_0);
                field.setLastInput(ZNACHENIE_POLYA_X);
            }else {
                field.setCell(i, j, ZNACHENIE_POLYA_X);
                field.setLastInput(ZNACHENIE_POLYA_0);
            }
        }
        assertEquals("Проверка на заполнение нисходящей диагонали.",true,field.isFilledLine());
    }

    @Test
    public void testAIWhatFinishLineWith2ItsMarkInRow() {
        Field field = new Field();
        RulesOfGameAndLogic rog = new RulesOfGameAndLogic(field);
        field.eraseField();

        int rand = (int)(Math.random()*2);
        int j = (int)(Math.random()*3);
        int randI = (int)(Math.random()*3);

        for (int i = 0; i < 3; i++) {
            if ((rand == 0) & (i != randI)) {
                field.setCell(i, j, ZNACHENIE_POLYA_0);
                field.setLastInput(ZNACHENIE_POLYA_X);
            }else if (i != randI) {
                field.setCell(i, j, ZNACHENIE_POLYA_X);
                field.setLastInput(ZNACHENIE_POLYA_0);
            }
        }

        rog.makeAMove();

        assertEquals("Проверка на заполнение последней ячейки в строке, при уже двух помеченных ячейках той же отметкой.",true,field.getCell(randI, j) == field.getLastInput());
        assertEquals("Проверка на заполнение строки после заполнения последней из трёх ячеек таким же символом.",true,field.isFilledLine());
    }

    @Test
    public void testAIWhatFinishLineWith2EnemyMarkInRow() {
        Field field = new Field();
        RulesOfGameAndLogic rog = new RulesOfGameAndLogic(field);
        field.eraseField();

        int rand = (int)(Math.random()*2);
        int j = (int)(Math.random()*3);
        int randI = (int)(Math.random()*3);

        for (int i = 0; i < 3; i++) {
            if ((rand == 0) & (i != randI)) {
                field.setCell(i, j, ZNACHENIE_POLYA_0);
                field.setLastInput(ZNACHENIE_POLYA_X);
            }else if (i != randI) {
                field.setCell(i, j, ZNACHENIE_POLYA_X);
                field.setLastInput(ZNACHENIE_POLYA_0);
            }
        }

        if (field.getLastInput() == ZNACHENIE_POLYA_X) {
            field.setLastInput(ZNACHENIE_POLYA_0);
        } else {
            field.setLastInput(ZNACHENIE_POLYA_X);
        }

        rog.makeAMove();

        assertEquals("Проверка на заполнение последней ячейки в строке, при уже двух помеченных ячейках противоположной отметкой.",true,field.getCell(randI, j) == field.getLastInput());
        assertEquals("Проверка на заполнение строки после заполнения последней из трёх ячеек противоположным символом.",false,field.isFilledLine());
    }

    @Test
    public void testAIWhatFinishLineWith2ItsMarkInColumn() {
        Field field = new Field();
        RulesOfGameAndLogic rog = new RulesOfGameAndLogic(field);
        field.eraseField();
        int rand = (int)(Math.random()*2);
        int i = (int)(Math.random()*3);
        int randJ = (int)(Math.random()*3);
        for (int j = 0; j < 3; j++) {
            if ((rand == 0) & (j != randJ)) {
                field.setCell(i, j, ZNACHENIE_POLYA_0);
                field.setLastInput(ZNACHENIE_POLYA_X);
            }else if (j != randJ) {
                field.setCell(i, j, ZNACHENIE_POLYA_X);
                field.setLastInput(ZNACHENIE_POLYA_0);
            }
        }

        rog.makeAMove();

        assertEquals("Проверка на заполнение последней ячейки в столбцы, при уже двух помеченных ячейках той же отметкой.",true,field.getCell(i, randJ) == field.getLastInput());
        assertEquals("Проверка на заполнение столбца после заполнения последней из трёх ячеек таким же символом.",true,field.isFilledLine());
    }

    @Test
    public void testAIWhatFinishLineWith2EnemyMarkInColumn() {
        Field field = new Field();
        RulesOfGameAndLogic rog = new RulesOfGameAndLogic(field);
        field.eraseField();

        int rand = (int)(Math.random()*2);
        int i = (int)(Math.random()*3);
        int randJ = (int)(Math.random()*3);

        for (int j = 0; j < 3; j++) {
            if ((rand == 0) & (j != randJ)) {
                field.setCell(i, j, ZNACHENIE_POLYA_0);
                field.setLastInput(ZNACHENIE_POLYA_X);
            }else if (j != randJ) {
                field.setCell(i, j, ZNACHENIE_POLYA_X);
                field.setLastInput(ZNACHENIE_POLYA_0);
            }
        }

        if (field.getLastInput() == ZNACHENIE_POLYA_X) {
            field.setLastInput(ZNACHENIE_POLYA_0);
        } else {
            field.setLastInput(ZNACHENIE_POLYA_X);
        }

        rog.makeAMove();

        assertEquals("Проверка на заполнение последней ячейки в столбце, при уже двух помеченных ячейках противоположной отметкой.",true,field.getCell(i, randJ) == field.getLastInput());
        assertEquals("Проверка на заполнение столбца после заполнения последней из трёх ячеек противоположным символом.",false,field.isFilledLine());
    }

    @Test
    public void testAIWhatFinishLineWith2ItsMarkInNisxodyawDiagonal() {
        Field field = new Field();
        RulesOfGameAndLogic rog = new RulesOfGameAndLogic(field);
        field.eraseField();
        int rand = (int)(Math.random()*2);
        int randINotSetCell = (int)(Math.random()*2);

        for (int i = 0, j = 0; i < 3; i++, j++) {
            if ((rand == 0)&(randINotSetCell != i)) {
                field.setCell(i, j, ZNACHENIE_POLYA_0);
                field.setLastInput(ZNACHENIE_POLYA_X);
            } else if (randINotSetCell != i) {
                field.setCell(i, j, ZNACHENIE_POLYA_X);
                field.setLastInput(ZNACHENIE_POLYA_0);
            }
        }

        rog.makeAMove();

        assertEquals("Проверка на заполнение нисходящей диагонали после заполнения первой или второй ячейки таким же символом.",true, field.getCell(randINotSetCell, randINotSetCell) == field.getLastInput());
        assertEquals("Проверка на заполнение нисходящей диагонали после заполнения последней из трёх ячеек таким же символом.",true,field.isFilledLine());

        field.eraseField();
        field.setLastInput(' ');
        rand = (int)(Math.random()*2);

        for (int i = 0, j = 0; i < 3; i++, j++) {
            if ((rand == 0) & ( i!=2 )) {
                field.setCell(i, j, ZNACHENIE_POLYA_0);
                field.setLastInput(ZNACHENIE_POLYA_X);
            } else if (i != 2) {
                field.setCell(i, j, ZNACHENIE_POLYA_X);
                field.setLastInput(ZNACHENIE_POLYA_0);
            }
        }

        rog.makeAMove();


        assertEquals("Проверка на заполнение последней ячейки в нисходящей диагонали, при уже двух первых помеченных ячейках той же отметкой.",true, field.getCell(2, 2) == field.getLastInput());
        assertEquals("Проверка на заполнение нисходящей диагонали после заполнения последней из трёх ячеек таким же символом.",true,field.isFilledLine());
    }

    @Test
    public void testAIWhatFinishLineWith2EnemyMarkInNisxodyawDiagonal() {
        Field field = new Field();
        RulesOfGameAndLogic rog = new RulesOfGameAndLogic(field);
        field.eraseField();
        int rand = (int)(Math.random()*2);
        int randINotSetCell = (int)(Math.random()*2);

        for (int i = 0, j = 0; i < 3; i++, j++) {
            if ((rand == 0)&(randINotSetCell != i)) {
                field.setCell(i, j, ZNACHENIE_POLYA_0);
                field.setLastInput(ZNACHENIE_POLYA_X);
            } else if (randINotSetCell != i) {
                field.setCell(i, j, ZNACHENIE_POLYA_X);
                field.setLastInput(ZNACHENIE_POLYA_0);
            }
        }

        if (field.getLastInput() == ZNACHENIE_POLYA_X) {
            field.setLastInput(ZNACHENIE_POLYA_0);
        } else {
            field.setLastInput(ZNACHENIE_POLYA_X);
        }

        rog.makeAMove();

        assertEquals("Проверка на заполнение последней пустой ячейки (первой или второй) нисходящей диагонали противоположным символом.",true, field.getCell(randINotSetCell, randINotSetCell) == field.getLastInput());
        assertEquals("Проверка на заполнение нисходящей диагонали после заполнения последней ячейки противоположным символом.",false,field.isFilledLine());

        field.eraseField();
        field.setLastInput(' ');
        rand = (int)(Math.random()*2);

        for (int i = 0, j = 0; i < 3; i++, j++) {
            if ((rand == 0) & ( i!=2 )) {
                field.setCell(i, j, ZNACHENIE_POLYA_0);
                field.setLastInput(ZNACHENIE_POLYA_X);
            } else if (i != 2) {
                field.setCell(i, j, ZNACHENIE_POLYA_X);
                field.setLastInput(ZNACHENIE_POLYA_0);
            }
        }

        if (field.getLastInput() == ZNACHENIE_POLYA_X) {
            field.setLastInput(ZNACHENIE_POLYA_0);
        } else {
            field.setLastInput(ZNACHENIE_POLYA_X);
        }

        rog.makeAMove();

        assertEquals("Проверка на заполнение третьей ячейки в нисходящей диагонали, при уже двух первых помеченных ячейках противоположной отметкой.",true, field.getCell(randINotSetCell, randINotSetCell) == field.getLastInput());
        assertEquals("Проверка на заполнение нисходящей диагонали после заполнения последней из трёх ячеек противоположной отметкой.",false,field.isFilledLine());
    }

    @Test
    public void testAIWhatFinishLineWith2ItsMarkInVosxodyawDiagonal() {
        Field field = new Field();
        RulesOfGameAndLogic rog = new RulesOfGameAndLogic(field);
        field.eraseField();
        int rand = (int)(Math.random()*2);
        int randINotSetCell = (int)(Math.random()*2);

        for (int i = 0, j = 3; i < 3; i++, j--) {
            if ((rand == 0)&(randINotSetCell != i)) {
                field.setCell(i, j, ZNACHENIE_POLYA_0);
                field.setLastInput(ZNACHENIE_POLYA_X);
            } else if (randINotSetCell != i) {
                field.setCell(i, j, ZNACHENIE_POLYA_X);
                field.setLastInput(ZNACHENIE_POLYA_0);
            }
        }

        rog.makeAMove();

        assertEquals("Проверка на заполнение восходящей диагонали после заполнения первой или второй ячейки таким же символом.",true, field.getCell(randINotSetCell, randINotSetCell) == field.getLastInput());
        assertEquals("Проверка на заполнение восходящей диагонали после заполнения последней из трёх ячеек таким же символом.",true,field.isFilledLine());

        field.eraseField();
        field.setLastInput(' ');
        rand = (int)(Math.random()*2);

        for (int i = 0, j = 3; i < 3; i++, j--) {
            if ((rand == 0) & ( i!=2 )) {
                field.setCell(i, j, ZNACHENIE_POLYA_0);
                field.setLastInput(ZNACHENIE_POLYA_X);
            } else if (i != 2) {
                field.setCell(i, j, ZNACHENIE_POLYA_X);
                field.setLastInput(ZNACHENIE_POLYA_0);
            }
        }

        rog.makeAMove();


        assertEquals("Проверка на заполнение последней ячейки в восходящей диагонали, при уже двух первых помеченных ячейках той же отметкой.",true, field.getCell(2, 2) == field.getLastInput());
        assertEquals("Проверка на заполнение восходящей диагонали после заполнения последней из трёх ячеек таким же символом.",true,field.isFilledLine());
    }

    @Test
    public void testAIWhatFinishLineWith2EnemyMarkInVosxodyawDiagonal() {
        Field field = new Field();
        RulesOfGameAndLogic rog = new RulesOfGameAndLogic(field);
        field.eraseField();
        int rand = (int)(Math.random()*2);
        int randINotSetCell = (int)(Math.random()*2);

        for (int i = 0, j = 3; i < 3; i++, j--) {
            if ((rand == 0)&(randINotSetCell != i)) {
                field.setCell(i, j, ZNACHENIE_POLYA_0);
                field.setLastInput(ZNACHENIE_POLYA_X);
            } else if (randINotSetCell != i) {
                field.setCell(i, j, ZNACHENIE_POLYA_X);
                field.setLastInput(ZNACHENIE_POLYA_0);
            }
        }

        if (field.getLastInput() == ZNACHENIE_POLYA_X) {
            field.setLastInput(ZNACHENIE_POLYA_0);
        } else {
            field.setLastInput(ZNACHENIE_POLYA_X);
        }

        rog.makeAMove();

        assertEquals("Проверка на заполнение последней пустой ячейки (первой или второй) восходящей диагонали противоположным символом.",true, field.getCell(randINotSetCell, randINotSetCell) == field.getLastInput());
        assertEquals("Проверка на заполнение восходящей диагонали после заполнения последней ячейки противоположным символом.",false,field.isFilledLine());

        field.eraseField();
        field.setLastInput(' ');
        rand = (int)(Math.random()*2);

        for (int i = 0, j = 3; i < 3; i++, j--) {
            if ((rand == 0) & ( i!=2 )) {
                field.setCell(i, j, ZNACHENIE_POLYA_0);
                field.setLastInput(ZNACHENIE_POLYA_X);
            } else if (i != 2) {
                field.setCell(i, j, ZNACHENIE_POLYA_X);
                field.setLastInput(ZNACHENIE_POLYA_0);
            }
        }

        if (field.getLastInput() == ZNACHENIE_POLYA_X) {
            field.setLastInput(ZNACHENIE_POLYA_0);
        } else {
            field.setLastInput(ZNACHENIE_POLYA_X);
        }

        rog.makeAMove();

        assertEquals("Проверка на заполнение третьей ячейки в восходящей диагонали, при уже двух первых помеченных ячейках противоположной отметкой.",true, field.getCell(randINotSetCell, randINotSetCell) == field.getLastInput());
        assertEquals("Проверка на заполнение восходящей диагонали после заполнения последней из трёх ячеек противоположной отметкой.",false,field.isFilledLine());
    }
}