package tanks.mobile;

import tanks.helpers.Destroyable;
import tanks.helpers.Direction;
import tanks.helpers.Drawable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Bullet implements Drawable, Destroyable {

    public static final int STEP = 1;
    private final int speed = 1;

    private AbstractTank tank;

    private int x;
    private int y;
    private Direction direction;
    private boolean destroyed;

    private Image image;

    public Bullet(AbstractTank tank, int x, int y, Direction direction) {
        this.tank = tank;
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.destroyed = false;
        try {
            image = ImageIO.read(new File("bullet.png"));
        } catch (IOException e) {
            System.err.println("Can't find image");
        }
    }

    public void updateX(int i) {
        x += i;
    }

    public void updateY(int i) {
        y += i;
    }

    @Override
    public void draw(Graphics g) {
        if (!destroyed) {
            g.drawImage(image, this.getX(), this.getY(), 14, 14, null);
        }
    }

    @Override
    public void destroy() {
        destroyed = true;
    }

    @Override
    public boolean isDestroyed() {
        return destroyed;
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