public class Main {

    public static void main(String[] args) {
        Field f = new Field();
        new Controller(f, new RulesOfGame(f)).run();
    }
}
