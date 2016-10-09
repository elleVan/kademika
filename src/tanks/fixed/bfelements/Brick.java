package tanks.fixed.bfelements;

import tanks.ActionField;
import tanks.helpers.Destroyable;
import tanks.fixed.AbstractBFElement;
import tanks.helpers.Drawable;

import java.awt.*;

public class Brick extends AbstractBFElement implements Drawable, Destroyable {

    public Brick(int x, int y, ActionField af) {
        super(x, y, af);
        color = Color.blue;
    }

    @Override
    public void destroy() {
        isEmpty = true;
        x = -100;
        y = -100;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());
        g.fillRect(x, y, 64, 64);
    }
}
