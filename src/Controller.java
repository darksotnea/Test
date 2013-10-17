import java.util.Scanner;

public class Controller {

    private Field gameField;
    private RulesOfGameAndLogic rulOfGame;
    boolean playWithComp = true;

    public Controller(Field field, RulesOfGameAndLogic rog) {

        this.gameField=field;
        this.rulOfGame=rog;
    }

    public void run() {

        int cellX;
        int cellY;
        char cellMark;
        boolean isCompPlay;

        gameField.eraseField();
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Чем будете заполнять?(нажмите 'x' или '0'(ноль)): ");
            cellMark = scanner.next().toCharArray()[0];
            if ((cellMark == 'x') | (cellMark == '0')) {
                break;
            } else {
                System.out.println("Неправильный ввод! Введите 0 или x.");
            }
        }
        System.out.println("Вы желаете чтобы за второго игрока ходил компьютер?(Нажмите Y(Yes) или N(No)): ");
        String compOrHuman = scanner.next();
        if (compOrHuman.equalsIgnoreCase("n")) {
            isCompPlay = false;
        } else {
            isCompPlay = true;
            System.out.println("Кто ходит первым?(Нажмите H(Human) или C(Computer): ");
            compOrHuman = scanner.next();
            if (compOrHuman.equalsIgnoreCase("c")) {
                gameField.setLastInput(cellMark);
                rulOfGame.makeAMove();
            } else {
                gameField.showField();
            }
        }

        while(true) {
            System.out.print("Введите номер вертикали(1-3): ");
            while(scanner.hasNext()) {
                if(scanner.hasNextInt()) {
                    cellX = scanner.nextInt()-1;
                    System.out.println();
                } else {
                    System.out.println("Неправильный ввод! Должна быть цифра! Введите ещё раз.");
                    scanner.next();
                    break;
                }

                System.out.print("Введите номер горизонтали(1-3): ");
                if (scanner.hasNextInt()) {
                    cellY = scanner.nextInt() - 1;
                    System.out.println();
                } else {
                    System.out.println("Неправильный ввод! Должна быть цифра! Введите ещё раз.");
                    scanner.next();
                    break;
                }
                gameField.setCell(cellX, cellY, cellMark);
                if (isCompPlay == false) {
                    if (cellMark == 'x') {
                        cellMark = '0';
                    } else {
                        cellMark = 'x';
                    }
                }
                if (rulOfGame.isFinish()) {
                    System.out.println("Игра закончена!");
                    System.exit(0);
                }
                if (isCompPlay) {
                    rulOfGame.makeAMove();
                    if (rulOfGame.isFinish()) {
                        System.out.println("Игра закончена!");
                        System.exit(0);
                    }
                }
                System.out.println();
                System.out.print("Введите номер вертикали(1-3): ");
            }
        }
    }
}