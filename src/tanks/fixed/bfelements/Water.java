package tanks.fixed.bfelements;

import tanks.fixed.AbstractBFElement;
import java.awt.*;

public class Water extends AbstractBFElement {

    public Water(int x, int y) {
        super(x, y);
        setColor(Color.cyan);
        setImageName("water.jpg");
    }
}
