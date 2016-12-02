package tanks.fixed.bfelements;

import tanks.fixed.AbstractBFElement;
import tanks.helpers.Destroyable;

import java.awt.*;

public class Eagle extends AbstractBFElement implements Destroyable{

    public Eagle(int x, int y) {
        super(x, y);
        setColor(Color.orange);
        setImageName("eagle.png");
    }

    @Override
    public void destroy() {
        setDestroyed(true);
    }
}
