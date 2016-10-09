package tanks.fixed;

import tanks.ActionField;
import tanks.helpers.Drawable;

import java.awt.*;

public class AbstractBFElement implements Drawable {

    protected boolean isEmpty;

    protected int x;
    protected int y;

    protected Color color;

    private ActionField af;

    public AbstractBFElement(int x, int y, ActionField af) {
        this.x = x;
        this.y = y;
        this.af = af;
        isEmpty = false;
    }

    @Override
    public void draw(Graphics g) {
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public Color getColor() {
        return color;
    }

    public ActionField getAf() {
        return af;
    }
}
