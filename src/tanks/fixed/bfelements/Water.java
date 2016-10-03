package tanks.fixed.bfelements;

import tanks.ActionField;
import tanks.fixed.AbstractBFElement;

import java.awt.*;

public class Water extends AbstractBFElement {

    public Water(ActionField af) {
        super(af);
        setCode("W");
        setColor(Color.cyan);
    }
}
