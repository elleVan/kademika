package tanks.fixed;

import tanks.ActionField;
import tanks.helpers.Destroyable;
import tanks.helpers.Drawable;

import java.awt.*;

public class AbstractBFElement implements Drawable, Destroyable {

    protected int x;
    protected int y;

    protected Color color;
    protected boolean isDestroyed = false;

    public AbstractBFElement(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(Graphics g) {
        if (!isDestroyed) {
            g.setColor(this.color);
            g.fillRect(this.getX(), this.getY(), 64, 64);
        }
    }

    @Override
    public void destroy() {
        isDestroyed = true;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getColor() {
        return color;
    }
}
