package tanks.fixed.bfelements;

import tanks.fixed.AbstractBFElement;
import tanks.helpers.Destroyable;

import java.awt.*;

public class Brick extends AbstractBFElement implements Destroyable{

    public Brick(int x, int y) {
        super(x, y);
        setColor(Color.blue);
        setImageName("brick.jpg");
    }

    @Override
    public void destroy() {
        setDestroyed(true);
    }
}
