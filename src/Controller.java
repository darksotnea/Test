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
        boolean isCompOrHuman;

        gameField.eraseField();
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Вы желаете чтобы за второго игрока ходил компьютер?(y,n): ");
        String compOrHuman = scanner.next();
        if (compOrHuman.equalsIgnoreCase("n")) {
            isCompOrHuman = false;
        } else {
            isCompOrHuman = true;
            System.out.println("Кто ходит первым?(h,c)");
            compOrHuman = scanner.next();
            if (compOrHuman.equalsIgnoreCase("c")) {
                rulOfGame.makeAMove();
            }
        }

        while(true) {
            System.out.print("Введите номер горизонтали(1-3): ");
            while(scanner.hasNext()) {
                if(scanner.hasNextInt()) {
                    cellX = scanner.nextInt()-1;
                    System.out.println();
                } else {
                    System.out.println("Неправильный ввод! Должна быть цифра! Введите ещё раз.");
                    scanner.next();
                    break;
                }

                System.out.print("Введите номер вертикали(1-3): ");
                if (scanner.hasNextInt()) {
                    cellY = scanner.nextInt() - 1;
                    System.out.println();
                } else {
                    System.out.println("Неправильный ввод! Должна быть цифра! Введите ещё раз.");
                    scanner.next();
                    break;
                }

                System.out.print("Чем заполнить?(нажмите 'x' или '0'): ");
                cellMark = scanner.next().toCharArray()[0];
                if ((cellMark == 'x') | (cellMark == '0')) {
                    gameField.setCell(cellX, cellY, cellMark);
                    if (rulOfGame.isFinish()) {
                        System.out.println("Игра закончена!");
                        System.exit(0);
                    }
                    if (isCompOrHuman) {
                        rulOfGame.makeAMove();
                        if (rulOfGame.isFinish()) {
                            System.out.println("Игра закончена!");
                            System.exit(0);
                        }
                    }
                    System.out.println();
                    System.out.print("Введите номер горизонтали(1-3): ");
                } else {
                    System.out.println("Неправильный ввод! Введите 0 или x.");
                    break;
                }
            }
        }
    }
}