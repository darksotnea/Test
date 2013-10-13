public class RulesOfGame {

    private Field gameField;

    public RulesOfGame(Field field) {
        this.gameField=field;
    }

    public boolean isFinish() {
        if (gameField.isFilledLine()) {
            System.out.println("Партия закончена! Победа!");
            return true;
        }
        if (gameField.isFilled()) {
            System.out.println("Партия закончена! Ничья! Ходов больше нет!");
            return true;
        }
        return false;
    }


}
