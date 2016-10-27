package tanks.fixed.bfelements;

import tanks.fixed.AbstractBFElement;
import java.awt.*;

public class Rock extends AbstractBFElement {

    public Rock(int x, int y) {
        super(x, y);
        color = Color.gray;
    }
}
