public class RulesOfGameAndLogic {

    private Field gameField;
    char aiMark;
    char playerMark;
    char playerMarkH;
    char aiMarkH;



    public RulesOfGameAndLogic(Field field) {
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

    void makeAMove() {

        if (gameField.getLastInput() == 'x') {
            playerMark = 'x';
            playerMarkH = 'X';
            aiMark = '0';
            aiMarkH = '0';
        } else {
            playerMark = '0';
            playerMarkH = '0';
            aiMark = 'x';
            aiMarkH = 'X';
        }

        Field.Cell bestCell = findBestMove(gameField);
        System.out.println();
        System.out.println("Компьютер ходит: ");
        gameField.setCell(bestCell.cellX, bestCell.cellY, aiMark);
    }

    private Field.Cell findBestMove(Field field) {

        // Алгоритм нахождения лучшего поля.
        // проверить, есть ли линия с уже заполненными 2 полями своего символа и свободной ячейкой в этой линии, занять её
        // если такой нету, проверить есть ли линия с уже заполненными 2 полями чужого символа и занять свободную ячейку.
        // если таких линий нет, проверить центральное поле и если оно свободно, занять его
        // если дошло до сюда, то занять любую ячейку линии, в которой уже есть свой символ
        // если это первый ход, то рандомно занять центральную или любую угловую ячейку

        field.cell.cellX = -1;

        field.cell = field.findLineWith2MarkAndReturnFreeCell(aiMarkH);
        if (field.cell.cellX != -1) {
            return field.cell;
        }

        field.cell = field.findLineWith2MarkAndReturnFreeCell(playerMarkH);
        if (field.cell.cellX != -1) {
            return field.cell;
        }

        if (field.getCell(1, 1) == ' ') {

            field.cell.cellX=1;
            field.cell.cellY=1;

            return field.cell;
        }

        field.cell = field.findLineWith1MarkAndReturnRandomFreeCell(aiMarkH);
        if (field.cell.cellX != -1) {
            return field.cell;
        }

        Field.Cell cellTest = field.randomFreeCell();
        return cellTest;
    }

}