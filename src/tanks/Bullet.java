package tanks;

public class Bullet {

    private int STEP = 1;
    private int speed = 1; // 5

    private int x;
    private int y;
    private int direction;

    public Bullet(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public void updateX(int i) {
        x += i;
    }

    public void updateY(int i) {
        y += i;
    }

    public void destroy() {
        x = -100;
        y = -100;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDirection() {
        return direction;
    }

    public int getSpeed() {
        return speed;
    }

    public int getSTEP() {
        return STEP;
    }
}