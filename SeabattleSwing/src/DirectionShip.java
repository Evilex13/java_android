/**
 * Created by a.tsoy on 08.10.2015.
 * Класс для позиционирования ориентации корабля по горизонтали и вертикале.
 */
class DirectionShip {
    private boolean direction;
    private int d1;
    private int d2;

    public DirectionShip(boolean direction) {
        this.direction = direction;
    }

    public int getD1() {
        return d1;
    }

    public int getD2() {
        return d2;
    }

    public DirectionShip invoke() {
        if (direction) {
            d1 = 1;
            d2 = 0;
        } else {
            d1 = 0;
            d2 = 1;
        }
        return this;
    }
}
