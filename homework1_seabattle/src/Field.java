import java.util.Random;

/**
 * Created by Alexander Tsoi on 27.09.15.
 */
public class Field {
    private static final int SIZE = 10;
    static final String[] ALPHABET = {"А","Б","В","Г","Д","Е","Ж","З","И","К"};

    char[][] cells = new char[SIZE][SIZE];
    int[][] cellsWithShips = new int[SIZE][SIZE];
    Ship[] ships = new Ship[SIZE];



    void init() { // clear, fill, redraw, init
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                cells[i][j] = '.';
                cellsWithShips[i][j] = 0;
            }
        }
    }

    boolean isNotGameOver() {
        boolean b = true;
        if (Ship.countShips != 0) b = false;
        return b;
    }

    //показать игровое поле
    void show() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                System.out.print(cells[i][j] + " ");
            }
            System.out.println("|" + (i + 1));
        }
        for (char[] cell : cells) {
            System.out.print("- ");
        }
        System.out.println();
        for (int i = 0; i < cells.length; i++) {
            System.out.print(ALPHABET[i] + " ");
        }
        System.out.println();
        System.out.println();
    }

    //выстрел
    void doShoot(int shootRow, int shootColumn) { // aiming
        switch (cellsWithShips[shootRow][shootColumn]) {
            case 0:
                System.out.println();
                System.out.println("Промах");
                System.out.println();
                cells[shootRow][shootColumn] = '*';
                cellsWithShips[shootRow][shootColumn] = -1;
                break;
            case -1:
                System.out.println();
                System.out.println("Уже стреляли, сделайте другой выстрел");
                System.out.println();
                break;
            case 1:
            case 2:
            case 3:
            case 4:
                checkShipHealth(shootRow, shootColumn);
                break;
            default:
                System.out.println("ERROR");
        }
    }

    //проверка на попадание в корабль
    void checkShipHealth(int shootRow, int shootColumn) {
        for (Ship ship : ships) {
            if (ship.getStatus() == Ship.SHIP_KILLED) continue;
            for (int i = 0; i < ship.getPoint().length; i++) {
                if ((ship.getPoint()[i].getRow() == shootRow) && (ship.getPoint()[i].getColumn() == shootColumn)) {
                    if ((ship.getHealth() == 1)) {
                        System.out.println();
                        System.out.println("Корабль уничтожен!");
                        System.out.println();
                        Ship.countShips--;
                        ship.setStatus(Ship.SHIP_KILLED);
                        cells[shootRow][shootColumn] = 'Х';
                        cellsWithShips[shootRow][shootColumn] = -1;
                        int n = ship.getHealth();
                        n--;
                        ship.setHealth(n);
                        Point.paintsShipsNearCells(ship, this);
                    } else if (ship.getHealth() != 1) {
                        System.out.println();
                        System.out.println("Есть попадание! Корабль горит!");
                        System.out.println();
                        ship.setStatus(Ship.SHIP_INJURED);
                        cells[shootRow][shootColumn] = 'Х';
                        cellsWithShips[shootRow][shootColumn] = -1;
                        int n = ship.getHealth();
                        n--;
                        ship.setHealth(n);
                    }
                }
            }
        }
    }

    //печать легенды игры
    void printLegend() {
        System.out.println("ПРАВИЛА ИГРЫ!!!");
        System.out.println("Для того чтобы сделать выстрел надо вводить: ");
        System.out.println("Ряд - числа от 1 до 10, Столбцы - буквы на русском языке от 'А' до 'К'");
        System.out.println("'.' - позиция для выстрела");
        System.out.println("'Х' - попадание в корабль");
        System.out.println("'*' - промах");
        System.out.println();
    }

    //расстановка кораблей
    void paint() {
        boolean b;
        for (int i = 3; i >= 0; i--) {
            for (int j = 0; j < 4 - i; j++) {
                do {
                    Point[] points = new Point[i + 1];
                    Point point = new Point();
                    DirectionShip direction1 = new DirectionShip(new Random().nextBoolean()).invoke();
                    b = false;
                    for (int k = 0; k < i + 1; k++) {
                        if (!Point.free(point.getRow() + direction1.getD1() * k, point.getColumn() + direction1.getD2() * k, this))
                            b = true;
                    }
                    if (!b) {
                        for (int k = 0; k < i + 1; k++) {
                            int row = point.getRow() + direction1.getD1() * k;
                            int column = point.getColumn() + direction1.getD2() * k;
                            this.cellsWithShips[row][column] = i + 1;
//                            this.cells[row][column] = 'O'; //для теста!
                            points[k] = new Point();
                            points[k].setRow(row);
                            points[k].setColumn(column);
                        }
                        ships[Ship.countShips] = new Ship(i + 1, points, Ship.SHIP_WELL, i + 1);
                    }
                } while (b);
            }
        }
    }
}
