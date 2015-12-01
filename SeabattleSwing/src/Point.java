import java.util.Random;

/**
 * Created by Alexander Tsoi  on 27.09.15.
 */
public class Point {
    private int column;
    private int row;
    static int[][] d = new int[8][2];// массив для проверки 8 ячеек вокруг ячейки

    static {
        d[0][0] = 0;
        d[1][0] = 1;
        d[2][0] = 0;
        d[3][0] = -1;
        d[4][0] = 1;
        d[5][0] = -1;
        d[6][0] = 1;
        d[7][0] = -1;
        d[0][1] = 1;
        d[1][1] = 0;
        d[2][1] = -1;
        d[3][1] = 0;
        d[4][1] = 1;
        d[5][1] = 1;
        d[6][1] = -1;
        d[7][1] = -1;
    }

    Point() {
        setRow(new Random().nextInt(10));
        setColumn(new Random().nextInt(10));
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    //Проверяем можно свободна ли ячейка и рядом стоящие с ней.
    static boolean free(int row, int column, Field field) {
        int dx, dy;
        if ((row >= 0) && (row < 10) && (column >= 0) && (column < 10) && (field.cellsWithShips[row][column]) == 0) {
            for (int i = 0; i < 8; i++) {
                dx = row + d[i][0];
                dy = column + d[i][1];
                if ((dx >= 0) && (dx < 10) && (dy >= 0) && (dy < 10) && (field.cellsWithShips[dx][dy]) != 0) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    //заливаем поле вокруг убитого корабля
    static void paintsShipsNearCells(Ship ship, Field field) {
        int dx, dy;
        for (int i = 0; i < ship.getPoint().length; i++) {
            int row = ship.getPoint()[i].getRow();
            int column = ship.getPoint()[i].getColumn();
            if ((ship.getPoint()[i].getRow() >= 0) && (ship.getPoint()[i].getRow() < 10) && (ship.getPoint()[i].getColumn() >= 0) && (ship.getPoint()[i].getColumn() < 10))
                for (int j = 0; j < 8; j++) {
                    dx = row + d[j][0];
                    dy = column + d[j][1];
                    if ((dx >= 0) && (dx < 10) && (dy >= 0) && (dy < 10) && (field.cellsWithShips[dx][dy] == 0)) {
                        field.cells[dx][dy] = '*';
                        field.cellsWithShips[dx][dy] = -1;
                    }
                }
        }
    }
}

