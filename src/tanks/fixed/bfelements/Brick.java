package tanks.fixed.bfelements;

import tanks.ActionField;
import tanks.helpers.Destroyable;
import tanks.fixed.AbstractBFElement;

import java.awt.*;

public class Brick extends AbstractBFElement implements Destroyable {

    public Brick(ActionField af) {
        super(af);
        setCode("B");
        setColor(Color.blue);
    }

    @Override
    public void destroy() {
        setCode("");
    }
}
