package tanks.fixed.bfelements;

import tanks.ActionField;
import tanks.fixed.AbstractBFElement;
import tanks.helpers.Drawable;

import java.awt.*;

public class Water extends AbstractBFElement implements Drawable {

    public Water(int x, int y, ActionField af) {
        super(x, y, af);
        color = Color.cyan;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());
        g.fillRect(x, y, 64, 64);
    }
}
