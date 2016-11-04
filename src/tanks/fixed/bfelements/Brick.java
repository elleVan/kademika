package tanks.fixed.bfelements;

import tanks.fixed.AbstractBFElement;
import tanks.helpers.Destroyable;

import java.awt.*;

public class Brick extends AbstractBFElement implements Destroyable{

    public Brick(int x, int y) {
        super(x, y);
        color = Color.blue;
    }

    @Override
    public void destroy() {
        isDestroyed = true;
    }
}
