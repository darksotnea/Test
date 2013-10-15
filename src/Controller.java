import java.util.Scanner;

public class Controller {

    private Field gameField;
    private RulesOfGame rulOfGame;

    public Controller(Field field, RulesOfGame rog) {

        this.gameField=field;
        this.rulOfGame=rog;
    }

    public void run() {

        gameField.eraseField();
        System.out.println();
        System.out.print("Введите номер горизонтали(1-3): ");
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextInt()) {
            int cellX = scanner.nextInt()-1;
            System.out.println();
            System.out.print("Введите номер вертикали(1-3): ");
            int cellY = scanner.nextInt()-1;
            System.out.println();
            System.out.print("Чем заполнить?(нажмите 'x' или '0'): ");
            char cellMark = scanner.next().toCharArray()[0];
            gameField.setCell(cellX,cellY,cellMark);
            if(rulOfGame.isFinish()){
                System.out.println("Игра закончена!");
                System.exit(0);
            }
            System.out.println();
            System.out.print("Введите номер горизонтали(1-3): ");
        }
    }
}
