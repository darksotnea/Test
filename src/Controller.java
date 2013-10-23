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

        gameField.eraseField();
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.print("Введите номер горизонтали(1-3): ");
            while(scanner.hasNext()) {
                if(scanner.hasNextInt()) {
                    cellX = scanner.nextInt()-1;
                    System.out.println();
                } else {
                    System.out.println("Не правильный ввод! Должна быть цифра! Введите ещё раз.");
                    scanner.next();
                    break;
                }

                System.out.print("Введите номер вертикали(1-3): ");
                if (scanner.hasNextInt()) {
                    cellY = scanner.nextInt() - 1;
                    System.out.println();
                } else {
                    System.out.println("Не правильный ввод! Должна быть цифра! Введите ещё раз.");
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
                    System.out.println();
                    System.out.print("Введите номер горизонтали(1-3): ");
                } else {
                    System.out.println("Не правильный ввод! Введите 0 или x.");
                    break;
                }
            }
        }
    }
}

/*      
Я бы на вашем месте все проверки данных перенёс в одно место. Иначе получается, что данные проверяются несколько
раз в разных местах. Методы setCell() и proverkaVvoda() ни к чему, если вы сразу корректно проверите данные. Так
же я бы советовал проверять каждый ввод последовательно. Ведь если человек сразу ввёл неправильные данные после
слов "Введите номер горизонтали(1-3): ", то его сразу нужно отправлять на повторный ввод. Я имею ввиду ввод
цифр, выходящих за пределы поля. Как раз та проверка, которая происходит в методе proverkaVvoda(). Только её
сразу нужно делать, а нечерез вызов двух лишних методов.
Так же жду ваших комментариев на мой код: https://github.com/Itallium
*/
