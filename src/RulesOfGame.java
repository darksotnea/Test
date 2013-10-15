public class RulesOfGame {

    private Field gameField;

    public RulesOfGame(Field field) {
        this.gameField=field;
    }

    public boolean isFinish() {

        if (gameField.isFilled()) {
            System.out.println("Партия закончена! Ничья! Ходов больше нет!");
            return true;
        }

        if (gameField.isFilledLine()) {
            System.out.println("Партия закончена! Победа!");
            return true;
        }

        return false;
    }


}