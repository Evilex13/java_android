/**
 * Created by Alexander Tsoi  on 27.09.15.
 */
public class SeaBattle {

    public static void main(String[] args) { // должен быть коротким
        doGame();
    }

    private static void doGame() {
        Field field = new Field();
        Player player = new Player();

        field.init();
        field.paint();

        System.out.println("ИГРА НАЧАЛАСЬ");
        field.printLegend();

//        statusShips(field); //для теста!

        do {
            field.show();
            System.out.println("Сделайте выстрел");
            field.doShoot(player.getShootRow(), player.getShootColumn());
//            statusShips(field); //для теста!
        } while (!field.isNotGameOver());

        field.show();
        System.out.println("Игра окончена! Все корабли уничтожены");
    }

//    метод для теста!
//    private static void statusShips(Field field) {
//        for (Ship ship:field.ships) {
//            System.out.println("Размер " + ship.getSize() + " Row " + ship.getPoint()[0].getRow() + " Column " + ship.getPoint()[0].getColumn() + " Health " + ship.getHealth());
//        }
//        System.out.println(Ship.countShips);
//    }
}
