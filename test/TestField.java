/**
 * Created with IntelliJ IDEA.
 * User: a.shupov
 * Date: 10.10.13
 * Time: 15:31
 * To change this template use File | Settings | File Templates.
 */

import org.junit.Test;
import static junit.framework.Assert.*;

public class TestField {
    private final char ZNACHENIE_POLYA_0 = '0';
    private final char ZNACHENIE_POLYA_X = 'x';

    @Test
    public void testWhatFilled() {
        Field field = new Field();
        int rand = (int)(Math.random()*2);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (rand == 0) {
                    field.setCell(i, j, ZNACHENIE_POLYA_0);
                }else {
                    field.setCell(i, j, ZNACHENIE_POLYA_X);
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
                    } else {
                        field.setCell(i, j, ZNACHENIE_POLYA_X);
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
        int rand = (int)(Math.random()*2);
        int i = (int)(Math.random()*3);
        for (int j = 0; j < 3; j++) {
            if (rand == 0) {
                field.setCell(i, j, ZNACHENIE_POLYA_0);
            }else {
                field.setCell(i, j, ZNACHENIE_POLYA_X);
            }
        }
        assertEquals("Проверка на заполнение столбца.",true,field.isFilledLine());
    }

    @Test
    public void testWhatFilledLineRows() {
        Field field = new Field();
        int rand = (int)(Math.random()*2);
        int j = (int)(Math.random()*3);
        for (int i = 0; i < 3; i++) {
            if (rand == 0) {
                field.setCell(i, j, ZNACHENIE_POLYA_0);
            }else {
                field.setCell(i, j, ZNACHENIE_POLYA_X);
            }
        }
        assertEquals("Проверка на заполнение строки.",true,field.isFilledLine());
    }

    @Test
    public void testWhatFilledLineDiagonals() {
        Field field = new Field();
        int rand = (int)(Math.random()*2);
        for (int i = 2, j = 0; j < 3; i--, j++) {
            if (rand == 0) {
                field.setCell(i, j, ZNACHENIE_POLYA_0);
            }else {
                field.setCell(i, j, ZNACHENIE_POLYA_X);
            }
        }
        assertEquals("Проверка на заполнение восходящей диагонали.",true,field.isFilledLine());

        field.eraseField();
        for (int i = 0, j = 0; j < 3; i++, j++) {
            if (rand == 0) {
                field.setCell(i, j, ZNACHENIE_POLYA_0);
            }else {
                field.setCell(i, j, ZNACHENIE_POLYA_X);
            }
        }
        assertEquals("Проверка на заполнение нисходящей диагонали.",true,field.isFilledLine());
    }
}