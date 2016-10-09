package tanks.fixed.bfelements;

import tanks.ActionField;
import tanks.helpers.Destroyable;
import tanks.fixed.AbstractBFElement;
import tanks.helpers.Drawable;
import tanks.mobile.tanks.Tiger;

import java.awt.*;

public class Rock extends AbstractBFElement implements Drawable, Destroyable {

    public Rock(int x, int y, ActionField af) {
        super(x, y, af);
        color = Color.gray;
    }

    @Override
    public void destroy() {
        if (getAf().getBullet().getTank() instanceof Tiger) {
            isEmpty = true;
            x = -100;
            y = -100;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());
        g.fillRect(x, y, 64, 64);
    }
}
