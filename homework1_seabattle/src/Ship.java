/**
 * Created by Alexander Tsoi on 27.09.15.
 */
public class Ship {
    public final static int SHIP_WELL = 1;
    public final static int SHIP_INJURED = 2;
    public final static int SHIP_KILLED = 3;
    static int countShips;

    private Point[] point;
    private int size;
    private int health;
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Point[] getPoint() {
        return point;
    }

    public int getSize() {
        return size;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    Ship(){}

    Ship(int size, Point[] point, int status, int health) {
        this.size = size;
        this.point = point;
        this.status = status;
        this.health = health;
        countShips++;
    }
}

