package tanks.fixed.bfelements;

import tanks.fixed.AbstractBFElement;
import java.awt.*;

public class Blank extends AbstractBFElement {

    public Blank(int x, int y) {
        super(x, y);
        setColor(new Color(180, 180, 180));
        setImageName("blank.jpg");
    }
}
