public class Main {

    public static void main(String[] args) {
        Field f = new Field();
        new Controller(f, new RulesOfGameAndLogic(f)).run();
    }
    /*
        У вас ввод неправильный. Горизонталь - у вас это столбец. Вертикаль - у вас это строка. А должно быть наоборот.
        Я себе мозг сломал пока понял, что не так в вашем алгоритме =) Ввожу одни данные, а знак ставится вообще в
        другом месте!!!
        Так же жду ваших комментариев на мой код: https://github.com/Itallium
     */
}
