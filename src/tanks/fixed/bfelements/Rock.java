package tanks.fixed.bfelements;

import tanks.fixed.AbstractBFElement;
import tanks.helpers.Destroyable;

import java.awt.*;

public class Rock extends AbstractBFElement implements Destroyable {

    public Rock(int x, int y) {
        super(x, y);
        color = Color.gray;
    }

    @Override
    public void destroy() {
        isDestroyed = true;
    }
}
