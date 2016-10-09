package tanks.mobile;

import lessonsJD.lesson1.Tank;
import tanks.helpers.Destroyable;
import tanks.helpers.Direction;
import tanks.helpers.Drawable;

import java.awt.*;

public class Bullet implements Drawable, Destroyable {

    public static final int STEP = 1;
    private final int speed = 1;

    private AbstractTank tank;

    private int x;
    private int y;
    private Direction direction;

    public Bullet(AbstractTank tank, int x, int y, Direction direction) {
        this.tank = tank;
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

    @Override
    public void destroy() {
        x = -100;
        y = -100;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(255, 255, 0));
        g.fillRect(x, y, 14, 14);
    }

    public AbstractTank getTank() {
        return tank;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getSpeed() {
        return speed;
    }
}